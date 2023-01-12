package io.nettapotek;


import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;

import javax.xml.bind.JAXBContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.UUID;

public class M9na {

    public String getM9na1() throws Exception {

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

        JAXBContext jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA1.class);
        var um = jaxbContext.createUnmarshaller();
        var m = jaxbContext.createMarshaller();
        var is = M9na.class.getClassLoader().getResourceAsStream("sarepta-eksempler/ERM9NA1");
        var msgHead = (MsgHead) um.unmarshal(is);

        msgHead.getMsgInfo().setMsgId(UUID.randomUUID().toString());
        msgHead.getMsgInfo().setGenDate(getXMLGregorianCalendarNow());

        var properties = new Properties();
        properties.load(M9na.class.getClassLoader().getResourceAsStream("sarepta-eksempler/nettapotek.properties"));

        for (var ident : msgHead.getMsgInfo().getSender().getOrganisation().getIdent()) {
            switch (ident.getTypeId().getV()) {
                case "ENH" -> ident.setId(properties.getProperty("orgnr"));
                case "LOK" -> ident.setId(properties.getProperty("lok"));
                case "AKO" -> ident.setId(properties.getProperty("ako"));
                case "HER" -> ident.setId(properties.getProperty("her"));
            }
        }
        for (var ident : msgHead.getMsgInfo().getPatient().getIdent()) {
            if (ident.getTypeId().getV().equals("FNR") || ident.getTypeId().getV().equals("DNR")) {
                ident.setId(properties.getProperty("identNr"));
            }
        }

        var fagmelding = (M9NA1) msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        fagmelding.getIdKunde().setId(properties.getProperty("idKunde"));



        var sw = new StringWriter();
        m.marshal(msgHead, sw);
        return sw.toString();
    }



    public static XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }
}
