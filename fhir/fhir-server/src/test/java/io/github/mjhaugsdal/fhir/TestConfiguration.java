package io.github.mjhaugsdal.fhir;

import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {

    @Value("${rest.address}")
    String address;

    @Bean
    public PatientService patientService() {
        var jaxrsClient = new JAXRSClientFactoryBean();
        jaxrsClient.setServiceClass(PatientService.class);
        jaxrsClient.setAddress(address);
        return (PatientService) jaxrsClient.create();
    }
}
