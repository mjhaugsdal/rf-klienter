package io.github.mjhaugsdal.fhir.configuration;


import org.apache.cxf.endpoint.Server;
import io.github.mjhaugsdal.fhir.PatientServiceImpl;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Value("${rest.address}")
    String address;

    @Bean
    public Server restService() {
        var jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        jaxrsServerFactoryBean.setServiceClass(PatientServiceImpl.class);
        jaxrsServerFactoryBean.setAddress(address); //TODO portkonfig
        return jaxrsServerFactoryBean.create();
    }
}
