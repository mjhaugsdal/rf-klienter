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
import org.springframework.context.annotation.Configuration;
import org.springframework.test.util.TestSocketUtils;

import java.util.ArrayList;
import java.util.List;

import static io.github.mjhaugsdal.soap.WSTestUtils.setupWSSEServer;


@TestConfiguration
public class SoapTestConfiguration {

    static {
        System.setProperty("soap.address", "http://localhost:" + TestSocketUtils.findAvailableTcpPort());
    }

    @Value("${soap.sign}")
    boolean sign;

    @Value("${soap.encrypt}")
    boolean encrypt;

    @Value("${soap.address}")
    String address;
    List<Feature> featureList = new ArrayList<>();

    SoapTestConfiguration() {
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        featureList.add(loggingFeature);
    }


    @Bean
    public Server naEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new NaWebService());
        bean.setAddress(address + "/NA"); //TODO portkonfig
        bean.setFeatures(featureList);
        setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public Server rekvirentEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new RekvirentWebservice());
        bean.setAddress(address + "/Rekvirent"); //TODO portkonfig
        bean.setFeatures(featureList);
        setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public Server utlevererEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new UtlevererWebservice());
        bean.setAddress(address + "/Utleverer"); //TODO portkonfig
        bean.setFeatures(featureList);
        setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }
}
