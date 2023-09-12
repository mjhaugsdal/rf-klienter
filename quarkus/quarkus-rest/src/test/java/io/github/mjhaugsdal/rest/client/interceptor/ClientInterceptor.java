package io.github.mjhaugsdal.rest.client.interceptor;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import jakarta.inject.Inject;
import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
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
            System.err.println("Before writing " + context.getEntity());
            var entity = context.getEntity();
            var sw = new StringWriter();
            marshaller.marshal(entity, sw);

            KeyStore ks;

            ks = KeyStore.getInstance("JKS");
            var is = ClientInterceptor.class.getClassLoader().getResourceAsStream("client/client.keystore");
            ks.load(is, "password".toCharArray());
            var cert = ks.getCertificate("client");
            var list = new ArrayList<com.nimbusds.jose.util.Base64>();

            var base64 = new com.nimbusds.jose.util.Base64(Base64.getEncoder().encodeToString(cert.getEncoded()));
            list.add(base64);


            JWSSigner jwsSigner = new RSASSASigner((PrivateKey) ks.getKey("client", "password".toCharArray()));

            JWSObject jwsObject = new JWSObject(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).contentType(MediaType.APPLICATION_XML).x509CertChain(list).build(),
                    new Payload(sw.toString()));

            jwsObject.sign(jwsSigner);
            String s = jwsObject.serialize();

            JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM);
            Payload payload = new Payload(s);
            JWEObject jweObject = new JWEObject(header, payload);
            jweObject.encrypt(new RSAEncrypter((RSAPublicKey) ks.getCertificate("server").getPublicKey()));
            String jweString = jweObject.serialize();


            var old = context.getOutputStream();
            var stream = new ByteArrayOutputStream();
            context.setOutputStream(stream);
            context.proceed();

            old.write(jweString.getBytes());
//            context.proceed();
            System.err.println("After writing " + context.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
