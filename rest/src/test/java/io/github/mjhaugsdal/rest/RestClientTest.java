package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.M9Na1;
import io.github.mjhaugsdal.rest.types.M9Na2;
import jakarta.xml.bind.JAXBContext;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.jose.jaxrs.JweClientResponseFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JweWriterInterceptor;
import org.apache.cxf.rs.security.jose.jaxrs.JwsClientResponseFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JwsWriterInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class RestClientTest {
    @Test
    void testJweJwsCompactSerialization() throws Exception {
        var message = "Hello World!";
        var context = "jwsjwscompact";
        new NaWebServiceImpl(false, context);

        M9Na1 m9Na1 = new M9Na1();
        m9Na1.setDokument(message.getBytes(StandardCharsets.UTF_8));

        var bean = new JAXRSClientFactoryBean();
        bean.setAddress("http://localhost:8890/" + context + "/m9na1");
        bean.setResourceClass(NaWebService.class);

        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        bean.getFeatures().add(loggingFeature);

        var jweWriterInterceptor = new JweWriterInterceptor();
        var jwsWriterInterceptor = new JwsWriterInterceptor();
        var jweClientResponseFilter = new JweClientResponseFilter();
        var jwsClientResponseFilter = new JwsClientResponseFilter();

        List<Object> providers = new LinkedList<>();
        providers.add(jwsWriterInterceptor);
        providers.add(jweWriterInterceptor);
        providers.add(jweClientResponseFilter);
        providers.add(jwsClientResponseFilter);

        bean.setProviders(providers);

        bean.getProperties(true).put(
                "rs.security.encryption.out.properties",
                "client/client.properties"
        );
        bean.getProperties(true).put(
                "rs.security.signature.out.properties",
                "client/client-sign.properties"
        );
        bean.getProperties(true).put(
                "rs.security.encryption.in.properties",
                "client/client-in.properties"
        );
        bean.getProperties(true).put(
                "rs.security.signature.in.properties",
                "client/client-in-sign.properties"
        );
        bean.getProperties(true).put("jose.debug", true);

        WebClient client = bean.createWebClient()
                .type("application/xml; charset=UTF-8")
                .encoding("UTF-8").acceptEncoding("UTF-8");

        var response = client.post(m9Na1);

        var jaxbContext = JAXBContext.newInstance(M9Na2.class);
        var um = jaxbContext.createUnmarshaller();
        var m9na2 = (M9Na2) um.unmarshal((InputStream) response.getEntity());

        var byteDokument = (byte[]) m9na2.getDokument();

        Assertions.assertEquals(new String(byteDokument), "Hello World!");
    }

    @Test
    void testJweJwsJwk() throws Exception {

        var message = "Hello World!";
        var context = "jwejwsjwk";
        new NaWebServiceImpl(true, context);

        M9Na1 m9Na1 = new M9Na1();
        m9Na1.setDokument(message.getBytes(StandardCharsets.UTF_8));

        var bean = new JAXRSClientFactoryBean();
        bean.setAddress("http://localhost:8890/" + context + "/m9na1");
        bean.setResourceClass(NaWebService.class);

        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        bean.getFeatures().add(loggingFeature);

        var jweWriterInterceptor = new JweWriterInterceptor();
        var jwsWriterInterceptor = new JwsWriterInterceptor();
        var jweClientResponseFilter = new JweClientResponseFilter();
        var jwsClientResponseFilter = new JwsClientResponseFilter();

        List<Object> providers = new LinkedList<>();
        providers.add(jwsWriterInterceptor);
        providers.add(jweWriterInterceptor);
        providers.add(jweClientResponseFilter);
        providers.add(jwsClientResponseFilter);

        bean.setProviders(providers);

        bean.getProperties(true).put(
                "rs.security.encryption.out.properties",
                "client/jwk/client.properties"
        );
        bean.getProperties(true).put(
                "rs.security.signature.out.properties",
                "client/jwk/client-sign.properties"
        );
        bean.getProperties(true).put(
                "rs.security.encryption.in.properties",
                "client/jwk/client-in.properties"
        );
        bean.getProperties(true).put(
                "rs.security.signature.in.properties",
                "client/jwk/client-in-sign.properties"
        );
        bean.getProperties(true).put("jose.debug", true);
        bean.getProperties(true).put("rs.security.accept.public.key", "true");

        var client = bean.createWebClient()
                .type("application/xml; charset=UTF-8")
                .encoding("UTF-8");

        client.encoding("UTF-8");
        var response = client.post(m9Na1);

        var jaxbContext = JAXBContext.newInstance(M9Na2.class);
        var um = jaxbContext.createUnmarshaller();
        var m9na2 = (M9Na2) um.unmarshal((InputStream) response.getEntity());

        var byteDokument = (byte[]) m9na2.getDokument();
        Assertions.assertEquals(new String(byteDokument), "Hello World!");

    }
}
