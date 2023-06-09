package io.github.mjhaugsdal.soap.service;

import io.github.mjhaugsdal.soap.SoapTestConfiguration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.kith.xmlstds.eresept.m9na4._2016_06_06.M9NA4;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Import(SoapTestConfiguration.class)
class NaWebServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(NaWebServiceTest.class.getName());

    @Autowired
    NAWeb naWeb;

    static String testMessage = "Hello world!";

    @Test
    void naWebServiceVerify() {
        var message = new MV();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = naWeb.naWebServiceVerify(message);
        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void naWebServiceM9Na1() throws AppRecFault_Exception {
        var message = new M9Na1();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = naWeb.naWebServiceM9Na1(message);
        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void naWebServiceM9Na3() throws AppRecFault_Exception, IOException, JAXBException {
        var message = new M9Na3();
        var is = NaWebServiceTest.class.getClassLoader().getResourceAsStream("M9NA3_eks.xml");
        message.setDokument(is.readAllBytes());

        var m9Na4 = naWeb.naWebServiceM9Na3(message);
        var byteDocument = (byte[]) m9Na4.getDokument();
        LOG.info(new String(byteDocument, StandardCharsets.UTF_8));

        var jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA4.class);
        var um = jaxbContext.createUnmarshaller();
        var msgHead = (MsgHead) um.unmarshal(new ByteArrayInputStream(byteDocument));
        Assertions.assertTrue(msgHead.getMsgInfo().getType().getV().contains("ERM9NA4"));
    }
}