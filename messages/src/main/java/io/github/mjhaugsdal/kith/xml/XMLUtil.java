package io.github.mjhaugsdal.kith.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XMLUtil {

    private static JAXBContext jaxbContext;

    private static Marshaller marshaller;

    private static jakarta.xml.bind.Unmarshaller unmarshaller;

    public XMLUtil(Class<?>... classes) {
        try {
            jaxbContext = JAXBContext.newInstance(classes);
            unmarshaller = jaxbContext.createUnmarshaller();
            marshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Object unmarshall(String filePath) throws JAXBException {
        return unmarshaller.unmarshal(XMLUtil.class.getClassLoader().getResourceAsStream(filePath));
    }

    public Object unmarshall(String xml, Charset charset) throws JAXBException {
        return unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes(charset)));
    }

    public String marshall(Object objectToBeMarshalled) throws JAXBException {
        var sw = new StringWriter();
        marshaller.marshal(objectToBeMarshalled, sw);
        return sw.toString();
    }
}
