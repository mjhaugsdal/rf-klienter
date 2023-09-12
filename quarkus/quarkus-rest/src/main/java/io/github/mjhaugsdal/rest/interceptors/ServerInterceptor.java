package io.github.mjhaugsdal.rest.interceptors;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import jakarta.inject.Inject;
import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;

@Provider
@ConstrainedTo(RuntimeType.SERVER)
public class ServerInterceptor implements ReaderInterceptor, WriterInterceptor {

    @Inject
    Marshaller marshaller;
    @Inject
    Unmarshaller unmarshaller;

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        try {
            var ks = KeyStore.getInstance("JKS");
            ks.load(ServerInterceptor.class.getClassLoader().getResourceAsStream("server/server.keystore"), "password".toCharArray());
            var key = ks.getKey("server", "password".toCharArray());
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


//            System.err.println("Data: " + sw);
            return jweObject.serialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        System.err.println("Before writing " + context.getEntity());
        var entity = context.getEntity();
        context.proceed();
        System.err.println("After writing " + context.getEntity());
    }
}
