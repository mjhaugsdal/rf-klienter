package io.nettapotek;

import no.ergo.reseptformidleren.webservices.na.*;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import org.apache.cxf.interceptor.Fault;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class NaWebService implements NAWeb {
    @Override
    public AppRec naWebServiceVerify(MV parameters) {
        return new AppRec();
    }

    @Override
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {
        var m9na2 = new M9NA2();
        m9na2.setAntall(0);
        var m9na2Document = new M9Na2();
        String byteDocument;
        try {
            var jaxbContext = JAXBContext.newInstance(M9NA2.class);
            var marshaller = jaxbContext.createMarshaller();
            var stringWriter = new StringWriter();
            marshaller.marshal(m9na2, stringWriter);
            byteDocument = stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        m9na2Document.setDokument(byteDocument.getBytes(StandardCharsets.UTF_8));
        return m9na2Document;
    }

    @Override
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        var appRec = new no.kith.xmlstds.apprec._2004_11_21.AppRec();
        appRec.setId(UUID.randomUUID().toString());

        AppRecFault appRecFault = new AppRecFault();
        String byteDocument;

        try {
            var jaxbContext = JAXBContext.newInstance(no.kith.xmlstds.apprec._2004_11_21.AppRec.class);
            var marshaller = jaxbContext.createMarshaller();
            var stringWriter = new StringWriter();
            marshaller.marshal(appRec, stringWriter);
            byteDocument = stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        appRecFault.setDokument(byteDocument.getBytes(StandardCharsets.UTF_8));

        throw new AppRecFault_Exception("", appRecFault);
    }
}
