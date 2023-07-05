package io.github.mjhaugsdal.rest.configuration;


import io.github.mjhaugsdal.rest.NaWebService;
import io.github.mjhaugsdal.rest.RekvirentWebService;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.rs.security.jose.jaxrs.JweClientResponseFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JweWriterInterceptor;
import org.apache.cxf.rs.security.jose.jaxrs.JwsClientResponseFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JwsWriterInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class RestConfiguration {

    //    @Value("${soap.sign}")
//    boolean sign;
//
//    @Value("${soap.encrypt}")
//    boolean encrypt;
//
    @Value("${rest.na.address}")
    String naaddress;

    @Value("${rest.rekvirent.address}")
    String rekvirentaddress;


    List<Feature> featureList = new ArrayList<>();
    List<Object> providers = new LinkedList<>();

    RestConfiguration() {
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        featureList.add(loggingFeature);
        providers.add("com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider");

    }

    @Bean
    public NaWebService naWebService() {
        JAXRSClientFactoryBean jaxrsClientFactoryBean = new JAXRSClientFactoryBean();
        jaxrsClientFactoryBean.setServiceClass(NaWebService.class);
        jaxrsClientFactoryBean.setAddress(naaddress + "/NA"); //TODO portkonfig
        jaxrsClientFactoryBean.setFeatures(featureList);

        var jweWriterInterceptor = new JweWriterInterceptor();
        var jwsWriterInterceptor = new JwsWriterInterceptor();
        var jweClientResponseFilter = new JweClientResponseFilter();
        var jwsClientResponseFilter = new JwsClientResponseFilter();

        providers.add(jwsWriterInterceptor);
        providers.add(jweWriterInterceptor);
        providers.add(jweClientResponseFilter);
        providers.add(jwsClientResponseFilter);

        jaxrsClientFactoryBean.setProviders(providers);

        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.encryption.out.properties",
                "client/client.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.signature.out.properties",
                "client/client-sign.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.encryption.in.properties",
                "client/client-in.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.signature.in.properties",
                "client/client-in-sign.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put("jose.debug", true);

        return (NaWebService) jaxrsClientFactoryBean.create();
    }

    @Bean
    public RekvirentWebService rekvirentWebService() {
        var jaxrsClientFactoryBean = new JAXRSClientFactoryBean();
        jaxrsClientFactoryBean.setServiceClass(RekvirentWebService.class);
        jaxrsClientFactoryBean.setAddress(rekvirentaddress + "/Rekvirent"); //TODO portkonfig
        jaxrsClientFactoryBean.setFeatures(featureList);

        var jweWriterInterceptor = new JweWriterInterceptor();
        var jwsWriterInterceptor = new JwsWriterInterceptor();
        var jweClientResponseFilter = new JweClientResponseFilter();
        var jwsClientResponseFilter = new JwsClientResponseFilter();

        providers.add(jwsWriterInterceptor);
        providers.add(jweWriterInterceptor);
        providers.add(jweClientResponseFilter);
        providers.add(jwsClientResponseFilter);

        jaxrsClientFactoryBean.setProviders(providers);

        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.encryption.out.properties",
                "client/client.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.signature.out.properties",
                "client/client-sign.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.encryption.in.properties",
                "client/client-in.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put(
                "rs.security.signature.in.properties",
                "client/client-in-sign.properties"
        );
        jaxrsClientFactoryBean.getProperties(true).put("jose.debug", true);


        return (RekvirentWebService) jaxrsClientFactoryBean.create();
    }

//    @Bean
//    public Server utlevererServer() {
//        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
//        endpoint.setAddress("http://localhost:" + "8888" + "/Utleverer");
//        endpoint.setServiceClass(UtlevererWebServiceImpl.class);
//        return endpoint.create();
//    }
//    @Bean


//    public NAWeb naWeb() throws WSSecurityException {
//        var bean = new JaxWsProxyFactoryBean();
//        bean.setServiceClass(NAWeb.class);
//        bean.setAddress(address + "/NA"); //TODO portkonfig
//        bean.setFeatures(featureList);
//        WSUtils.setupWSSEClient(bean, encrypt, sign);
//        return (NAWeb) bean.create();
//    }

//    @Bean
//    public RekvirentWeb rekvirentWeb() throws WSSecurityException {
//        var bean = new JaxWsProxyFactoryBean();
//        bean.setServiceClass(RekvirentWeb.class);
//        bean.setAddress(address + "/Rekvirent"); //TODO portkonfig
//        bean.setFeatures(featureList);
//        WSUtils.setupWSSEClient(bean, encrypt, sign);
//        return (RekvirentWeb) bean.create();
//    }

//    @Bean
//    public UtlevererWeb utlevererWeb() throws WSSecurityException {
//        var bean = new JaxWsProxyFactoryBean();
//        bean.setServiceClass(UtlevererWeb.class);
//        bean.setAddress(address + "/Utleverer"); //TODO portkonfig
//        bean.setFeatures(featureList);
//        WSUtils.setupWSSEClient(bean, encrypt, sign);
//        return (UtlevererWeb) bean.create();
//    }

//    @Bean
//    public SoapClient soapClient(final NAWeb naWeb, final UtlevererWeb utlevererWeb, final RekvirentWeb rekvirentWeb) {
//        return new SoapClient(naWeb, rekvirentWeb, utlevererWeb);
//    }

}
