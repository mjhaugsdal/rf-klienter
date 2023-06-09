package io.github.mjhaugsdal.mockservice;

import io.github.mjhaugsdal.rest.NaWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServiceJAXRSConfiguration {

    @Autowired
    private Bus bus;

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("http://localhost:8882/rest");
        endpoint.setServiceClass(NaWebServiceImpl.class);
        return endpoint.create();
    }
}
