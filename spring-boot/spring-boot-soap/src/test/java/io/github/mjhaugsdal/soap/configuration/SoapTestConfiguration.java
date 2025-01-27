package io.github.mjhaugsdal.soap.configuration;

import io.github.mjhaugsdal.soap.service.NaWebService;
import io.github.mjhaugsdal.soap.service.RekvirentWebservice;
import io.github.mjhaugsdal.soap.service.UtlevererWebservice;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.util.TestSocketUtils;

import java.util.ArrayList;
import java.util.List;

import static io.github.mjhaugsdal.soap.WSTestUtils.setupWSSEServer;


@TestConfiguration
public class SoapTestConfiguration {

    @TestConfiguration
    @Profile("default")
    public static class DefaultConfiguration {
        @Value("${rekvirent.sign}")
        boolean rekvirentSign;

        @Value("${rekvirent.encrypt}")
        boolean rekvirentEncrypt;

        @Value("${rekvirent.address}")
        String rekvirentAddress;

        @Value("${utleverer.sign}")
        boolean utlevererSign;

        @Value("${utleverer.encrypt}")
        boolean utlevererEncrypt;

        @Value("${utleverer.address}")
        String utlevererAddress;

        @Value("${na.sign}")
        boolean naSign;

        @Value("${na.encrypt}")
        boolean naEncrypt;

        @Value("${na.address}")
        String naAddress;
        List<Feature> featureList = new ArrayList<>();

        DefaultConfiguration() {
            var loggingFeature = new LoggingFeature();
            loggingFeature.setPrettyLogging(true);
            featureList.add(loggingFeature);
        }

        static {
            System.setProperty("rekvirent.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort() + "/v1");
            System.setProperty("utleverer.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort() + "/v1");
            System.setProperty("na.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort() + "/v1");
            System.setProperty("rest.server.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort());
        }

        @Bean
        public Server naEndpoint() {
            var bean = new JaxWsServerFactoryBean();
            bean.setServiceBean(new NaWebService());
            bean.setAddress(naAddress + "/NA");
            bean.setFeatures(featureList);
            setupWSSEServer(bean, naEncrypt, naSign);
            return bean.create();
        }

        @Bean
        public Server rekvirentEndpoint() {
            var bean = new JaxWsServerFactoryBean();
            bean.setServiceBean(new RekvirentWebservice());
            bean.setAddress(rekvirentAddress + "/Rekvirent");
            bean.setFeatures(featureList);
            setupWSSEServer(bean, rekvirentEncrypt, rekvirentSign);
            return bean.create();
        }

        @Bean
        public Server utlevererEndpoint() {
            var bean = new JaxWsServerFactoryBean();
            bean.setServiceBean(new UtlevererWebservice());
            bean.setAddress(utlevererAddress + "/Utleverer");
            bean.setFeatures(featureList);
            setupWSSEServer(bean, utlevererEncrypt, utlevererSign);
            return bean.create();
        }

    }

    @TestConfiguration
    @Profile("local")
    public static class LocalConfiguration {
        List<Feature> featureList = new ArrayList<>();

        LocalConfiguration() {
            var loggingFeature = new LoggingFeature();
            loggingFeature.setPrettyLogging(true);
            featureList.add(loggingFeature);
        }
    }
}
