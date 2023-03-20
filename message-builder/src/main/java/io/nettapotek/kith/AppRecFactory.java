package io.nettapotek.kith;

import no.kith.xmlstds.apprec._2004_11_21.AppRec;
import no.kith.xmlstds.apprec._2004_11_21.CS;
import no.kith.xmlstds.apprec._2004_11_21.HCP;
import no.kith.xmlstds.apprec._2004_11_21.OriginalMsgId;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class AppRecFactory {

    public static AppRec buildApprec(MsgHead msgHead) {

        var apprec = AppRec.appRecBuilder()
                .withGenDate(getXmlgregorianCalendar())
                .withMsgType(createCS("", ""))
                .withId(UUID.randomUUID().toString())
                .withStatus(createCS("", ""))
                .withSender(createApprecSender())
                .withReceiver(createApprecReceiver())
                .withSoftwareVersion("")
                .withMiGversion("migVersion")
                .withOriginalMsgId(createOriginalMsgId(msgHead))
                .build();

        return apprec;
    }

    private static AppRec.Receiver createApprecReceiver() {
        return AppRec.Receiver.receiverBuilder()
                .withHcp(createAppRecHCP())
                .withRole(createCS("",""))
                .build();
    }

    private static OriginalMsgId createOriginalMsgId(MsgHead msgHead) {
        return OriginalMsgId.originalMsgIdBuilder()
                .withId(msgHead.getMsgInfo().getMsgId())
                .withIssueDate(msgHead.getMsgInfo().getGenDate())
                .withMsgType(createCS(msgHead.getMsgInfo().getType().getDN(), msgHead.getMsgInfo().getType().getV()))
                .build();
    }

    private static HCP createAppRecHCP() {
        return HCP.HCPBuilder()
                .build();
    }

    private static AppRec.Sender createApprecSender() {
        return AppRec.Sender.senderBuilder()
                .withHcp(createAppRecHCP())
                .withRole(createCS("",""))
                .build();
    }

    private static CS createCS(String DN, String V) {
        return CS.CSBuilder()
                .withDn(DN)
                .withV(V)
                .build();
    }

    static XMLGregorianCalendar getXmlgregorianCalendar() {
        XMLGregorianCalendar xmlDate;
        var gc = new GregorianCalendar();
        gc.setTime(new Date());
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return xmlDate;
    }
}
