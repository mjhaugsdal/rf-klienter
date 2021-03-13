package im.haugsdal.playground.nettapotek.m9na1._2016_06_06;


import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import im.haugsdal.playground.nettapotek.handler.IMessageHandler;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.Reseptinfo;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import org.w3c.dom.Node;

/**
 * Whats my job?
 *
 *
 * Names subject to change... :)
 *
 * ->
 * (EncryptedPayloadHandler) Might not be necessary, AND might be handled by cxf interceptor...
 * To decrypt the XmlEnc data
 * ->
 * (SignatureHandler) Might not be necessary AND might be handler by cxf interceptor...
 * To check the signature
 * ->
 * AuthenticationHandler/HeaderHandler
 * Only picks out the headers we are interested in
 * ->
 * JwtHandler
 * One of the handlers after the JwtToken is parsed and available
 * ->
 *
 * TODO from here on, we are working with the actual request data..
 *
 * Around here we should log incoming message...
 *
 * (MsgHeadHandler)
 * All KITH messages will be a MsgHead, why not have single handler?
 * Responsibilities, evaluate the info in this part of the message..
 *
 * (Interface IMessageHandler)
 * (M9na1Handler implements IMessageHandler)
 *
 * Result from <Concrete>Handler, should be some the object to be passed back to client.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * https://refactoring.guru/design-patterns/chain-of-responsibility
 *
 *
 */
public class M9Na1Handler implements IMessageHandler {

    JAXBContext context = JAXBContext.newInstance(M9NA1.class.getPackage().getName());
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();


    public M9Na1Handler() throws JAXBException, ParserConfigurationException {
        dbf.setNamespaceAware(true);
        //empty
    }


    @Override
    public Object handleMessage(Object message) {
        MsgHead msgHead = (MsgHead) message;

        Node node = (Node) msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Object object = null;

        try {

            Unmarshaller parser = context.createUnmarshaller();
            object = parser.unmarshal(node);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        M9NA1 m9NA1 = (M9NA1) object;


        assert m9NA1 != null;
        String idKundeId = m9NA1.getIdKunde().getId();
        System.out.println(idKundeId);

        //More, of course..

        M9NA2 m9NA2 = new M9NA2();

        m9NA2.setAntall(0);
        Reseptinfo reseptinfo = new Reseptinfo();
        m9NA2.getReseptinfo().add(reseptinfo);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(M9NA2.class.getPackage().getName());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(m9NA2, sw);

            System.out.println(sw.toString());
            System.out.println("");


        } catch (JAXBException e) {
            e.printStackTrace();
        }


        //TODO bruk jaxb + https://github.com/mklemm/jaxb2-rich-contract-plugin for å lage builder til alle java/xml objektene.

        return m9NA2;
    }
}
