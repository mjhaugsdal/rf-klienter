package io.github.mjhaugsdal.kith.xml.m9na1;


import io.github.mjhaugsdal.kith.xml.MessageType;
import io.github.mjhaugsdal.kith.xml.Utils;
import no.kith.xmlstds.CV;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.felleskomponent1.Ident;
import no.kith.xmlstds.msghead._2006_05_24.CS;
import no.kith.xmlstds.msghead._2006_05_24.Document;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import no.kith.xmlstds.msghead._2006_05_24.MsgInfo;
import no.kith.xmlstds.msghead._2006_05_24.Patient;
import no.kith.xmlstds.msghead._2006_05_24.Receiver;
import no.kith.xmlstds.msghead._2006_05_24.RefDoc;
import no.kith.xmlstds.msghead._2006_05_24.Sender;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class M9na1 {

    public static M9naBuilder m9naBuilder() {
        return new M9naBuilder();
    }

    public MsgHead getMsgHead() {
        return msgHead;
    }

    private final MsgHead msgHead;

    public M9na1(M9naBuilder m9NaBuilder) {

        msgHead = MsgHead.msgHeadBuilder()
                .withMsgInfo(MsgInfo.msgInfoBuilder()
                        .withType(MessageType.M9NA1.getCS())
                        .withMiGversion("v1.2 2006-05-24")
                        .withGenDate(Utils.getXmlgregorianCalendar())
                        .withMsgId(UUID.randomUUID().toString())
                        .withReceiver(m9NaBuilder.receiver)
                        .withSender(m9NaBuilder.sender)
                        .withPatient(m9NaBuilder.patient)
                        .build())
                .addDocument(Document.documentBuilder()
                        .withDocumentConnection(CS.CSBuilder()
                                .withV("H")
                                .withDn("Hoveddokument")
                                .build())
                        .withRefDoc(RefDoc.refDocBuilder()
                                .withMsgType(CS.CSBuilder()
                                        .withV("XML")
                                        .withDn("XML-instans")
                                        .build())
                                .withContent(RefDoc.Content.contentBuilder()
                                        .addAny(M9NA1.m9NA1Builder()
                                                .withIdKunde(m9NaBuilder.ident)
                                                .withRefNr(m9NaBuilder.refNr)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .addDocument(Document.documentBuilder()
                        .withRefDoc(RefDoc.refDocBuilder()
                                .withMsgType(CS.CSBuilder()
                                        .withV("XML")
                                        .withDn("XML-instans")
                                        .build())
                                .withContent(RefDoc.Content.contentBuilder()
                                        .addAny(m9NaBuilder.innbyggerToken)
                                        .build())
                                .build())
                        .build())
                .build();
    }


    public static class M9naBuilder {

        private CV typeId;
        private Sender sender;
        private Patient patient;
        private Receiver receiver;
        private no.kith.xmlstds.felleskomponent1.Ident ident;
        private final List<String> refNr = new ArrayList<>();
        private Object innbyggerToken;


        public M9naBuilder withSender(Sender sender) {
            this.sender = sender;
            return this;
        }

        public M9naBuilder withPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public M9naBuilder withReceiver(Receiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public M9naBuilder withIdent(Ident ident) {
            this.ident = ident;
            return this;
        }

        public M9naBuilder withRefNr(String refNr) {
            this.refNr.add(refNr);
            return this;
        }

        public M9naBuilder withInnbyggerToken(Object token) {
            this.innbyggerToken = token;
            return this;
        }


        public M9na1 build() {
            return new M9na1(this);
        }
    }


}


//    public static String buildM9na1() throws Exception {

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

//        var jaxbContext = JAXBContext.newInstance(MsgHead.class, no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1.class);
//        var um = jaxbContext.createUnmarshaller();
//        var m = jaxbContext.createMarshaller();
//        var is = M9NA1.class.getClassLoader().getResourceAsStream("sarepta-eksempler/ERM9NA1");
//        var msgHead = (MsgHead) um.unmarshal(is);
//
//        var sw = new StringWriter();
//        m.marshal(msgHead, sw);
//        return sw.toString();
//    }
//
//
//    public static XMLGregorianCalendar getXMLGregorianCalendarNow()
//            throws DatatypeConfigurationException {
//        var gregorianCalendar = new GregorianCalendar();
//        var datatypeFactory = DatatypeFactory.newInstance();
//        return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
//    }
//
//
//    public static MsgHead makeM9na2(MsgHead msgHead) {
//
//        return null;
//    }


//}
