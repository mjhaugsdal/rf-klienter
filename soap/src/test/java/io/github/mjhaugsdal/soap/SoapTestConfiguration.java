package io.github.mjhaugsdal.soap;

import io.github.mjhaugsdal.soap.service.NaWebService;
import io.github.mjhaugsdal.soap.service.RekvirentWebservice;
import io.github.mjhaugsdal.soap.service.UtlevererWebservice;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SoapTestConfiguration {

    @Value("${soap.sign}")
    boolean sign;

    @Value("${soap.encrypt}")
    boolean encrypt;

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
        bean.setAddress("http://localhost:8881/NA"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public Server rekvirentEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new RekvirentWebservice());
        bean.setAddress("http://localhost:8881/Rekvirent"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public Server utlevererEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new UtlevererWebservice());
        bean.setAddress("http://localhost:8881/Utleverer"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public NAWeb naWeb() throws WSSecurityException {
        var bean = new JaxWsProxyFactoryBean();
        bean.setServiceClass(NAWeb.class);
        bean.setAddress("http://localhost:8881/NA"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEClient(bean, encrypt, sign);
        return (NAWeb) bean.create();
    }

    @Bean
    public RekvirentWeb rekvirentWeb() throws WSSecurityException {
        var bean = new JaxWsProxyFactoryBean();
        bean.setServiceClass(RekvirentWeb.class);
        bean.setAddress("http://localhost:8881/Rekvirent"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEClient(bean, encrypt, sign);
        return (RekvirentWeb) bean.create();
    }

    @Bean
    public UtlevererWeb utlevererWeb() throws WSSecurityException {
        var bean = new JaxWsProxyFactoryBean();
        bean.setServiceClass(UtlevererWeb.class);
        bean.setAddress("http://localhost:8881/Utleverer"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEClient(bean, encrypt, sign);
        return (UtlevererWeb) bean.create();
    }


}
