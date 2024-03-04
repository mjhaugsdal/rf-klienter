package io.github.mjhaugsdal.soap.configuration;

import io.github.mjhaugsdal.soap.SoapClient;
import io.github.mjhaugsdal.soap.util.WSUtils;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SoapConfiguration {

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

    @Value("${ecdh:false}")
    boolean ecdh;

    @Value("${na.address}")
    String naAddress;

    List<Feature> featureList = new ArrayList<>();

    SoapConfiguration() {
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        featureList.add(loggingFeature);
    }


    @Bean
    public NAWeb naWeb() throws WSSecurityException {
        var bean = new JaxWsProxyFactoryBean();
        bean.setServiceClass(NAWeb.class);
        bean.setAddress(naAddress + "/NA"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEClient(bean, naEncrypt, naSign, ecdh);
        return (NAWeb) bean.create();
    }

    @Bean
    public RekvirentWeb rekvirentWeb() throws WSSecurityException {
        var bean = new JaxWsProxyFactoryBean();
        bean.setServiceClass(RekvirentWeb.class);
        bean.setAddress(rekvirentAddress + "/Rekvirent"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEClient(bean, rekvirentEncrypt, utlevererSign, ecdh);
        return (RekvirentWeb) bean.create();
    }

    @Bean
    public UtlevererWeb utlevererWeb() throws WSSecurityException {
        var bean = new JaxWsProxyFactoryBean();
        bean.setServiceClass(UtlevererWeb.class);
        bean.setAddress(utlevererAddress + "/Utleverer"); //TODO portkonfig
        bean.setFeatures(featureList);
        WSUtils.setupWSSEClient(bean, utlevererEncrypt, utlevererSign, ecdh);
        return (UtlevererWeb) bean.create();
    }

    @Bean
    public SoapClient soapClient(final NAWeb naWeb, final UtlevererWeb utlevererWeb, final RekvirentWeb rekvirentWeb) {
        return new SoapClient(naWeb, rekvirentWeb, utlevererWeb);
    }
}
