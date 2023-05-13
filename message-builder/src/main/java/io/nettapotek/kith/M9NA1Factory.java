package io.nettapotek.kith;


import jakarta.xml.bind.JAXBContext;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringWriter;
import java.util.GregorianCalendar;

public class M9NA1Factory {

    public static String buildM9na1() throws Exception {

//
//        var msgInfo = MsgInfo.builder()
//                .withSender(null)
//                .withPatient(null)
//                .withReceiver(null)
//                .build();
//
//        CV typeId = CV.builder()
//                .withS("")
//                .withV("")
//                .build();
//
//        Ident ident = Ident.builder()
//                .withId("someId")
//                .withTypeId(typeId)
//                .build();
//
//        M9NA1 m9NA1 = M9NA1.builder()
//                .withRefNr("")
//                .withIdKunde(ident)
//                .build();
//
//
//        var content = RefDoc.Content.builder()
//                .withAny(m9NA1)
//                .build();
//
//        var refDoc = RefDoc.builder()
//                .withContent(content)
//                .build();
//
//        var document = Document.builder()
//                .withContentDescription("SomeContent")
//                .withRefDoc(refDoc)
//                .build();
//        var msgHead = MsgHead.builder()
//                .withMsgInfo(msgInfo)
//                .withDocument(document)
//                .build();

        var jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA1.class);
        var um = jaxbContext.createUnmarshaller();
        var m = jaxbContext.createMarshaller();
        var is = M9NA1Factory.class.getClassLoader().getResourceAsStream("sarepta-eksempler/ERM9NA1");
        var msgHead = (MsgHead) um.unmarshal(is);

        var sw = new StringWriter();
        m.marshal(msgHead, sw);
        return sw.toString();
    }


    public static XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        var gregorianCalendar = new GregorianCalendar();
        var datatypeFactory = DatatypeFactory.newInstance();
        return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
    }


    public static MsgHead makeM9na2(MsgHead msgHead) {

        return null;
    }


}
