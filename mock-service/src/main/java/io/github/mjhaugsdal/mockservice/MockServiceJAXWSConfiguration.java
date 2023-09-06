package io.github.mjhaugsdal.mockservice;

import io.github.mjhaugsdal.soap.WSTestUtils;
import io.github.mjhaugsdal.soap.service.NaWebService;
import io.github.mjhaugsdal.soap.service.RekvirentWebservice;
import io.github.mjhaugsdal.soap.service.UtlevererWebservice;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class MockServiceJAXWSConfiguration {

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
    List<Feature> featureList = new ArrayList();

    MockServiceJAXWSConfiguration() {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        this.featureList.add(loggingFeature);
    }

    @Bean
    public Server naEndpoint() {
        JaxWsServerFactoryBean bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new NaWebService());
        bean.setAddress(this.naAddress + "/NA");
        bean.setFeatures(this.featureList);
        WSTestUtils.setupWSSEServer(bean, this.naEncrypt, this.naSign);
        return bean.create();
    }

    @Bean
    public Server rekvirentEndpoint() {
        JaxWsServerFactoryBean bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new RekvirentWebservice());
        bean.setAddress(this.rekvirentAddress + "/Rekvirent");
        bean.setFeatures(this.featureList);
        WSTestUtils.setupWSSEServer(bean, this.rekvirentEncrypt, this.rekvirentSign);
        return bean.create();
    }

    @Bean
    public Server utlevererEndpoint() {
        JaxWsServerFactoryBean bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new UtlevererWebservice());
        bean.setAddress(this.utlevererAddress + "/Utleverer");
        bean.setFeatures(this.featureList);
        WSTestUtils.setupWSSEServer(bean, this.utlevererEncrypt, this.utlevererSign);
        return bean.create();
    }
}
