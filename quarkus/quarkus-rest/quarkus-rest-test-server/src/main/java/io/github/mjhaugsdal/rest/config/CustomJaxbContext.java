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

    @Singleton
    @Produces
    Marshaller marshaller(JAXBContext jaxbContext) {
        try {
            var marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshaller;

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}