package io.github.mjhaugsdal.kith.xml;

import no.kith.xmlstds.msghead._2006_05_24.Address;
import no.kith.xmlstds.msghead._2006_05_24.CS;
import no.kith.xmlstds.msghead._2006_05_24.Document;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import no.kith.xmlstds.msghead._2006_05_24.MsgInfo;
import no.kith.xmlstds.msghead._2006_05_24.Organisation;
import no.kith.xmlstds.msghead._2006_05_24.Receiver;
import no.kith.xmlstds.msghead._2006_05_24.Sender;

import java.util.List;
import java.util.UUID;

import static io.github.mjhaugsdal.kith.xml.Utils.getXmlgregorianCalendar;

public class MsgHeadBuilder {

    public static MsgHead buildResponseMessageHead(MsgHead msgHead, CS responseType, List<Document> documentList) {
        return MsgHead.msgHeadBuilder()
                .withMsgInfo(MsgInfo.msgInfoBuilder()
                        .withGenDate(getXmlgregorianCalendar())
                        .withMsgId(UUID.randomUUID().toString())
                        .withType(responseType)
                        .withSender(getSender())
                        .withReceiver(Receiver.receiverBuilder()
                                .withOrganisation(msgHead.getMsgInfo().getSender().getOrganisation())
                                .withComMethod(msgHead.getMsgInfo().getSender().getComMethod())
                                .build())
                        .withMiGversion("v1.2 2006-05-24")
                        .build()
                ).withDocument(documentList)
                .build();
    }

    public static Sender getSender() {
        return Sender.senderBuilder()
                .withOrganisation(Organisation.organisationBuilder()
                        .withAddress(Address.addressBuilder()
                                .withCity("Oslo")
                                .build())
                        .build())
                .build();
    }
}

