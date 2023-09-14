package io.github.mjhaugsdal.rest.config;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class CustomJaxbContext {

    // Replaces the CDI producer for JAXBContext built into Quarkus
    @Singleton
    @Produces
    JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance("io.github.mjhaugsdal.rest.types");
    }
}