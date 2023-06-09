package io.github.mjhaugsdal.mockservice;

import io.github.mjhaugsdal.rest.NaWebServiceImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServiceJAXRSConfiguration {

    @Value("${service.port}")
    int port;

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setAddress("http://localhost:" + port + "/rest");
        endpoint.setServiceClass(NaWebServiceImpl.class);
        return endpoint.create();
    }
}
