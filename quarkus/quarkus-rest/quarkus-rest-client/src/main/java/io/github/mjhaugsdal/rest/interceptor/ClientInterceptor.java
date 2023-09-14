package io.github.mjhaugsdal.rest.interceptor;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
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
import java.security.KeyStore;
import java.security.PrivateKey;
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

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        try {

            var ks = KeyStore.getInstance("JKS");
            ks.load(ServerInterceptor.class.getClassLoader().getResourceAsStream("client/client.keystore"), "password".toCharArray());
            var key = ks.getKey("client", "password".toCharArray());
            System.err.println("Before reading " + context.getGenericType());
//            Object entity = context.proceed();

            var is = context.getInputStream();
//            var sw = new StringWriter();


//            marshaller.marshal(entity, sw);

            var str = new String(is.readAllBytes());
            // Parse into JWE object again...
            var jweObject = JWEObject.parse(str);


            // Decrypt
            jweObject.decrypt(new RSADecrypter((PrivateKey) key));

            String s = jweObject.getPayload().toString();

// To parse the JWS and verify it, e.g. on client-side
            var jwsObject = JWSObject.parse(s);

            var x5c = jwsObject.getHeader().getX509CertChain().get(0).decode();
            var certFactory = CertificateFactory.getInstance("X509");
            var x509Certificate = certFactory.generateCertificate(new ByteArrayInputStream(x5c));

            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) x509Certificate.getPublicKey());

            LOGGER.info(String.valueOf(jwsObject.verify(verifier)));
            LOGGER.info(jwsObject.getPayload().toString());

            var unmarshalled = unmarshaller.unmarshal(new ByteArrayInputStream(jwsObject.getPayload().toBytes()));

            LOGGER.info(unmarshalled.toString());
            return unmarshalled;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        try {

            var ks = KeyStore.getInstance("JKS");
            var is = ClientInterceptor.class.getClassLoader().getResourceAsStream("client/client.keystore");
            ks.load(is, "password".toCharArray());
            var cert = ks.getCertificate("client");
            var list = new ArrayList<Base64>();

            var base64 = new com.nimbusds.jose.util.Base64(java.util.Base64.getEncoder().encodeToString(cert.getEncoded()));
            list.add(base64);

            var entity = context.getEntity();
            var sw = new StringWriter();
            marshaller.marshal(entity, sw);
            var jwsSigner = new RSASSASigner((PrivateKey) ks.getKey("client", "password".toCharArray()));
            var jwsObject = new JWSObject(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).contentType(MediaType.APPLICATION_XML).x509CertChain(list).build(),
                    new Payload(sw.toString()));

            jwsObject.sign(jwsSigner);
            String serializedJws = jwsObject.serialize();

            var header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM);
            var payload = new Payload(serializedJws);
            var jweObject = new JWEObject(header, payload);
            jweObject.encrypt(new RSAEncrypter((RSAPublicKey) ks.getCertificate("server").getPublicKey()));
            var serializedJwe = jweObject.serialize();

            context.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            context.setEntity(serializedJwe);
            context.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}