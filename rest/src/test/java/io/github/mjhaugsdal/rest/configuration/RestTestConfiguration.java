package io.github.mjhaugsdal.rest.configuration;

import io.github.mjhaugsdal.rest.NaWebServiceImpl;
import io.github.mjhaugsdal.rest.RekvirentWebServiceImpl;
import io.github.mjhaugsdal.rest.UtlevererWebServiceImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.rs.security.jose.jaxrs.JweContainerRequestFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JweWriterInterceptor;
import org.apache.cxf.rs.security.jose.jaxrs.JwsContainerRequestFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JwsWriterInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.util.TestSocketUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@TestConfiguration
public class RestTestConfiguration {

    static {
        System.setProperty("rest.na.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort());
        System.setProperty("rest.rekvirent.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort());
        System.setProperty("rest.utleverer.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort());
    }

    @Value("${rest.na.address}")
    String naAddress;

    @Value("${rest.rekvirent.address}")
    String rekvirentAddress;

    @Value("${rest.utleverer.address}")
    String utlevererAddress;

    @Value("${use.jwk}")
    String jwk;


    @Bean
    public Server naServer() {
        var serverFactoryBean = new JAXRSServerFactoryBean();
        serverFactoryBean.setServiceClass(NaWebServiceImpl.class);
        serverFactoryBean.setAddress(naAddress + "/" + "NA");
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        serverFactoryBean.getFeatures().add(loggingFeature);

        List<Object> providers = new LinkedList<>();
        providers.add("com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider");

        var jweContainerRequestFilter = new JweContainerRequestFilter();
        var jwsContainerRequestFilter = new JwsContainerRequestFilter();

        Properties properties = new Properties();
        try {
            properties.load(RestTestConfiguration.class.getClassLoader().getResourceAsStream("server/server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        var jweWriterInterceptor = new JweWriterInterceptor();
        jweWriterInterceptor.setUseJweOutputStream(true);

        var jwsWriterInterceptor = new JwsWriterInterceptor();
        jwsWriterInterceptor.setUseJwsOutputStream(true);

        providers.add(jweContainerRequestFilter);
        providers.add(jwsContainerRequestFilter);
        providers.add(jweWriterInterceptor);
        providers.add(jwsWriterInterceptor);

        serverFactoryBean.setProviders(providers);

        if (Boolean.parseBoolean(jwk)) {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/jwk/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/jwk/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/jwk/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/jwk/server-out-sign.properties"
            );

            serverFactoryBean.getProperties(true).put("rs.security.accept.public.key", "true");
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.public.key", "true");
        } else {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/server-out-sign.properties"
            );
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.cert", "true");
        }

        serverFactoryBean.getProperties(true).put("jose.debug", true);
        return serverFactoryBean.create();
    }

    @Bean
    public Server utlevererServer() {
        var serverFactoryBean = new JAXRSServerFactoryBean();
        serverFactoryBean.setServiceClass(UtlevererWebServiceImpl.class);
        serverFactoryBean.setAddress(utlevererAddress + "/" + "Utleverer");
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        serverFactoryBean.getFeatures().add(loggingFeature);

        List<Object> providers = new LinkedList<>();
        providers.add("com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider");

        var jweContainerRequestFilter = new JweContainerRequestFilter();
        var jwsContainerRequestFilter = new JwsContainerRequestFilter();

        Properties properties = new Properties();
        try {
            properties.load(RestTestConfiguration.class.getClassLoader().getResourceAsStream("server/server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        var jweWriterInterceptor = new JweWriterInterceptor();
        jweWriterInterceptor.setUseJweOutputStream(true);

        var jwsWriterInterceptor = new JwsWriterInterceptor();
        jwsWriterInterceptor.setUseJwsOutputStream(true);

        providers.add(jweContainerRequestFilter);
        providers.add(jwsContainerRequestFilter);
        providers.add(jweWriterInterceptor);
        providers.add(jwsWriterInterceptor);

        serverFactoryBean.setProviders(providers);

        if (Boolean.parseBoolean(jwk)) {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/jwk/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/jwk/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/jwk/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/jwk/server-out-sign.properties"
            );

            serverFactoryBean.getProperties(true).put("rs.security.accept.public.key", "true");
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.public.key", "true");
        } else {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/server-out-sign.properties"
            );
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.cert", "true");
        }

        serverFactoryBean.getProperties(true).put("jose.debug", true);
        return serverFactoryBean.create();


    }


    @Bean
    public Server rekvirentServer() {


        var serverFactoryBean = new JAXRSServerFactoryBean();
        serverFactoryBean.setServiceClass(RekvirentWebServiceImpl.class);
        serverFactoryBean.setAddress(rekvirentAddress + "/" + "Rekvirent");
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        serverFactoryBean.getFeatures().add(loggingFeature);

        List<Object> providers = new LinkedList<>();
        providers.add("com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider");

        var jweContainerRequestFilter = new JweContainerRequestFilter();
        var jwsContainerRequestFilter = new JwsContainerRequestFilter();

        Properties properties = new Properties();
        try {
            properties.load(RestTestConfiguration.class.getClassLoader().getResourceAsStream("server/server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        var jweWriterInterceptor = new JweWriterInterceptor();
        jweWriterInterceptor.setUseJweOutputStream(true);

        var jwsWriterInterceptor = new JwsWriterInterceptor();
        jwsWriterInterceptor.setUseJwsOutputStream(true);

        providers.add(jweContainerRequestFilter);
        providers.add(jwsContainerRequestFilter);
        providers.add(jweWriterInterceptor);
        providers.add(jwsWriterInterceptor);

        serverFactoryBean.setProviders(providers);

        if (Boolean.parseBoolean(jwk)) {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/jwk/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/jwk/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/jwk/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/jwk/server-out-sign.properties"
            );

            serverFactoryBean.getProperties(true).put("rs.security.accept.public.key", "true");
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.public.key", "true");
        } else {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/server-out-sign.properties"
            );
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.cert", "true");
        }

        serverFactoryBean.getProperties(true).put("jose.debug", true);
        return serverFactoryBean.create();


    }
}
