package io.github.mjhaugsdal.soap.service;

import io.github.mjhaugsdal.kith.xml.XMLUtil;
import io.github.mjhaugsdal.soap.configuration.SoapTestConfiguration;
import jakarta.xml.bind.JAXBException;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na4._2016_06_06.M9NA4;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Import(SoapTestConfiguration.class)
class NaWebServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(NaWebServiceTest.class.getName());

    @Autowired
    NAWeb naWeb;

    @Test
    void naWebServiceVerify() {
        var message = new MV();
        message.setDokument("Hello world!".getBytes(StandardCharsets.UTF_8));
        var apprec = naWeb.naWebServiceVerify(message);
        Assertions.assertEquals("Hello world!", new String((byte[]) apprec.getDokument()));
    }

    @Test
    void naWebServiceM9Na1() throws AppRecFault_Exception, IOException, JAXBException {
        var xmlUtil = new XMLUtil(M9NA2.class, MsgHead.class);
        var message = new M9Na1();
        var is = NaWebServiceTest.class.getClassLoader().getResourceAsStream("M9NA1_eks.xml");
        assert is != null;
        message.setDokument(is.readAllBytes());
        var m9na2Document = naWeb.naWebServiceM9Na1(message);
        var msgHead = (MsgHead) xmlUtil.unmarshall(new String((byte[]) m9na2Document.getDokument()), StandardCharsets.UTF_8);
        var fagmelding = msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(M9NA2.class, fagmelding);
    }

    @Test
    void naWebServiceM9Na3() throws AppRecFault_Exception, IOException, JAXBException {
        var xmlUtil = new XMLUtil(M9NA4.class, MsgHead.class);
        var message = new M9Na3();
        var is = NaWebServiceTest.class.getClassLoader().getResourceAsStream("M9NA3_eks.xml");
        assert is != null;
        message.setDokument(is.readAllBytes());
        var m9na4Document = naWeb.naWebServiceM9Na3(message);
        var msgHead = (MsgHead) xmlUtil.unmarshall(new String((byte[]) m9na4Document.getDokument()), StandardCharsets.UTF_8);
        var fagmelding = msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(M9NA4.class, fagmelding);
    }
}