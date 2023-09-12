package io.github.mjhaugsdal.rest.client.interceptor;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import jakarta.inject.Inject;
import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.*;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Base64;

@Provider
@ConstrainedTo(RuntimeType.CLIENT)
public class ClientInterceptor implements ReaderInterceptor, WriterInterceptor {

    @Inject
    Marshaller marshaller;
    @Inject
    Unmarshaller unmarshaller;

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        System.err.println("Before reading " + context.getGenericType());
        Object entity = context.proceed();

        var sw = new StringWriter();
        try {
            marshaller.marshal(entity, sw);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        System.err.println("Data: " + sw);
        return entity;
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        try {

            var ks = KeyStore.getInstance("JKS");
            var is = ClientInterceptor.class.getClassLoader().getResourceAsStream("client/client.keystore");
            ks.load(is, "password".toCharArray());
            var cert = ks.getCertificate("client");
            var list = new ArrayList<com.nimbusds.jose.util.Base64>();

            var base64 = new com.nimbusds.jose.util.Base64(Base64.getEncoder().encodeToString(cert.getEncoded()));
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
