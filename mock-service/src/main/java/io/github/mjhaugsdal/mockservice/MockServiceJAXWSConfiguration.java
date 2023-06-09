package io.github.mjhaugsdal.mockservice;

import io.github.mjhaugsdal.soap.service.NaWebService;
import io.github.mjhaugsdal.soap.service.RekvirentWebservice;
import io.github.mjhaugsdal.soap.service.UtlevererWebservice;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.metrics.MetricsFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static io.github.mjhaugsdal.mockservice.WSTestUtils.setupWSSEServer;

@Configuration
public class MockServiceJAXWSConfiguration {


    @Value("${service.port}")
    int port;

    boolean encrypt = true;
    boolean sign = true;

    List<Feature> featureList = new ArrayList<>();

    MockServiceJAXWSConfiguration() {
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        featureList.add(loggingFeature);
        featureList.add(new MetricsFeature());
    }

    @Bean
    public Server naEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new NaWebService());
        bean.setAddress("http://localhost:" + port + "/NA");
        bean.setFeatures(featureList);
        setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public Server rekvirentEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new RekvirentWebservice());
        bean.setAddress("http://localhost:" + port + "/Rekvirent");
        bean.setFeatures(featureList);
        setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }

    @Bean
    public Server utlevererEndpoint() {
        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new UtlevererWebservice());
        bean.setAddress("http://localhost:" + port + "/Utleverer");
        bean.setFeatures(featureList);
        setupWSSEServer(bean, encrypt, sign);
        return bean.create();
    }
}
