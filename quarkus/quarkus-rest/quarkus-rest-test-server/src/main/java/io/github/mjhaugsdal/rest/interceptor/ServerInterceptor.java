package io.github.mjhaugsdal.rest.interceptor;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
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
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;

@Provider
public class ServerInterceptor implements ReaderInterceptor, WriterInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerInterceptor.class.getName());
    private final RSASSASigner jwsSigner;
    private final Certificate serverCertificate;
    private final RSADecrypter rsaDecrypter;

    @Inject
    Marshaller marshaller;
    @Inject
    Unmarshaller unmarshaller;

    ServerInterceptor() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException {
        var ks = KeyStore.getInstance("JKS");
        ks.load(ServerInterceptor.class.getClassLoader().getResourceAsStream("server/server.keystore"), "password".toCharArray());
        var privateKey = ks.getKey("server", "password".toCharArray());
        serverCertificate = ks.getCertificate("server");
        rsaDecrypter = new RSADecrypter((PrivateKey) privateKey);
        jwsSigner = new RSASSASigner((PrivateKey) ks.getKey("server", "password".toCharArray()));
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        try (var is = context.getInputStream()) {
            // Parse into JWE object again...
            var jweObject = JWEObject.parse(new String(is.readAllBytes()));

            // Decrypt
            jweObject.decrypt(rsaDecrypter);
            var jwsObject = JWSObject.parse(jweObject.getPayload().toString());

            validateSignature(context, jwsObject);

            var unmarshalled = unmarshaller.unmarshal(new ByteArrayInputStream(jwsObject.getPayload().toBytes()));

            LOGGER.info(unmarshalled.toString());
            return unmarshalled;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String encrypt(String serializedJws, Certificate suppliedCertificate) throws JOSEException {
        var jweObject = new JWEObject(new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM),  new Payload(serializedJws));
        jweObject.encrypt(new RSAEncrypter((RSAPublicKey) suppliedCertificate.getPublicKey()));
        return jweObject.serialize();
    }

    private static void validateSignature(ReaderInterceptorContext context, JWSObject jwsObject) throws CertificateException, JOSEException {
        var x5c = jwsObject.getHeader().getX509CertChain().get(0).decode();
        var certFactory = CertificateFactory.getInstance("X509");
        var x509Certificate = certFactory.generateCertificate(new ByteArrayInputStream(x5c));
        var verifier = new RSASSAVerifier((RSAPublicKey) x509Certificate.getPublicKey());

        var validSignature = jwsObject.verify(verifier);
        if (!validSignature)
            throw new RuntimeException(); //TODO

        context.setProperty("suppliedCertificate", x509Certificate);
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        try {
            var suppliedCertificate = (Certificate) context.getProperty("suppliedCertificate");
            var entity = context.getEntity();

            var list = new ArrayList<Base64>();
            var base64 = new com.nimbusds.jose.util.Base64(java.util.Base64.getEncoder().encodeToString(serverCertificate.getEncoded()));
            list.add(base64);

            var sw = new StringWriter();
            marshaller.marshal(entity, sw);

            var jwsObject = new JWSObject(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).contentType(MediaType.APPLICATION_XML).x509CertChain(list).build(),
                    new Payload(sw.toString()));

            jwsObject.sign(jwsSigner);
            var serializedJwe = encrypt(jwsObject.serialize(), suppliedCertificate);
            context.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            context.setEntity(serializedJwe);
            context.proceed();
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}