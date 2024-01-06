package io.github.mjhaugsdal.kith.xml.na;

import io.github.mjhaugsdal.kith.xml.XMLUtil;
import jakarta.xml.bind.JAXBException;
import no.kith.xmlstds.base64container.Base64Container;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.eresept.m9na4._2016_06_06.M9NA4;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NettapotekTest {

    @Test
    void m9na1UnmarshallTest() throws JAXBException {
        var xmlUtil = new XMLUtil(MsgHead.class, M9NA1.class, Base64Container.class);
        var msgHead = (MsgHead) xmlUtil.unmarshall("Nettapotek/M9NA1_eks.xml");
        Assertions.assertInstanceOf(MsgHead.class, msgHead);
        var fagmelding = (M9NA1)msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(M9NA1.class, fagmelding);
        var vedlegg = msgHead.getDocument().get(1).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(Base64Container.class, vedlegg);
    }

    @Test
    void m9na2UnmarshallTest() throws JAXBException {
        var xmlUtil = new XMLUtil(MsgHead.class, M9NA2.class, Base64Container.class);
        var msgHead = (MsgHead) xmlUtil.unmarshall("Nettapotek/M9NA2_eks.xml");
        Assertions.assertInstanceOf(MsgHead.class, msgHead);
        var fagmelding = (M9NA2)msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(M9NA2.class, fagmelding);
    }

    @Test
    void m9na3UnmarshallTest() throws JAXBException {
        var xmlUtil = new XMLUtil(MsgHead.class, M9NA3.class, Base64Container.class);
        var msgHead = (MsgHead) xmlUtil.unmarshall("Nettapotek/M9NA3_eks.xml");
        Assertions.assertInstanceOf(MsgHead.class, msgHead);
        var fagmelding = (M9NA3)msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(M9NA3.class, fagmelding);
        var vedlegg = msgHead.getDocument().get(1).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(Base64Container.class, vedlegg);
    }

    @Test
    void m9na4UnmarshallTest() throws JAXBException {
        var xmlUtil = new XMLUtil(MsgHead.class, M9NA4.class, Base64Container.class);
        var msgHead = (MsgHead) xmlUtil.unmarshall("Nettapotek/M9NA4_eks.xml");
        Assertions.assertInstanceOf(MsgHead.class, msgHead);
        var fagmelding = (M9NA4)msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Assertions.assertInstanceOf(M9NA4.class, fagmelding);
    }
}