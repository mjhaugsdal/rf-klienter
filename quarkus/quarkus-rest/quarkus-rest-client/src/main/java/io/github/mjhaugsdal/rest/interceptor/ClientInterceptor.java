package io.github.mjhaugsdal.rest.interceptor;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.util.Base64;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;

@Provider
public class ClientInterceptor implements ReaderInterceptor, WriterInterceptor {


    private static final Logger LOGGER = LoggerFactory.getLogger(ClientInterceptor.class.getName());

    @Inject
    Marshaller marshaller;
    @Inject
    Unmarshaller unmarshaller;


    KeyStore keyStore = KeyStore.getInstance("JKS");

    CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");

    JWSSigner jwsSigner;

    Key rsaDecryptionKey;

    Certificate clientCertificate;

    Certificate serverPublicCertificate;

    ClientInterceptor() throws KeyStoreException, CertificateException {
        try {
            keyStore.load(ServerInterceptor.class.getClassLoader().getResourceAsStream("client/client.keystore"), "password".toCharArray());
            rsaDecryptionKey = keyStore.getKey("client", "password".toCharArray());
            jwsSigner = new RSASSASigner((PrivateKey) keyStore.getKey("client", "password".toCharArray()));
            clientCertificate = keyStore.getCertificate("client");
            serverPublicCertificate = keyStore.getCertificate("server");
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        try {

            var is = context.getInputStream();
            var str = new String(is.readAllBytes());
            var jweObject = JWEObject.parse(str);

            // Decrypt
            jweObject.decrypt(new RSADecrypter((PrivateKey) rsaDecryptionKey));
            String s = jweObject.getPayload().toString();
            var jwsObject = JWSObject.parse(s);

            //Get key from JWS x5c header
            var x5c = jwsObject.getHeader().getX509CertChain().get(0).decode();
            var x509Certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(x5c));
            var verifier = new RSASSAVerifier((RSAPublicKey) x509Certificate.getPublicKey());

            var validSignature = jwsObject.verify(verifier);
            if (!validSignature)
                throw new RuntimeException(); //TODO

            LOGGER.info("After decryption: {}", jwsObject.getPayload().toString());

            var unmarshalled = unmarshaller.unmarshal(new ByteArrayInputStream(jwsObject.getPayload().toBytes()));

            return unmarshalled;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        try {
            var x5cHeader = new ArrayList<Base64>();
            var base64EncodedCertificate = new com.nimbusds.jose.util.Base64(java.util.Base64.getEncoder().encodeToString(clientCertificate.getEncoded()));
            x5cHeader.add(base64EncodedCertificate);

            var entity = context.getEntity();
            var sw = new StringWriter();
            marshaller.marshal(entity, sw);

            LOGGER.info("Before encryption: {}", sw);

            var jwsObject = new JWSObject(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).contentType(MediaType.APPLICATION_XML).x509CertChain(x5cHeader).build(),
                    new Payload(sw.toString()));

            jwsObject.sign(jwsSigner);
            var serializedJws = jwsObject.serialize();
            var header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM);
            var payload = new Payload(serializedJws);
            var jweObject = new JWEObject(header, payload);
            jweObject.encrypt(new RSAEncrypter((RSAPublicKey) serverPublicCertificate.getPublicKey()));
            var serializedJwe = jweObject.serialize();

            context.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            context.setEntity(serializedJwe);
            context.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}