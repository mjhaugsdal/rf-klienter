package io.github.mjhaugsdal.soap.service;

import io.github.mjhaugsdal.kith.xml.MessageType;
import io.github.mjhaugsdal.kith.xml.MsgHeadBuilder;
import jakarta.annotation.Resource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.ws.WebServiceContext;
import no.ergo.reseptformidleren.webservices.na.AppRec;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na2;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.M9Na4;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.kith.xmlstds.CS;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.eresept.m9na4._2016_06_06.M9NA4;
import no.kith.xmlstds.msghead._2006_05_24.Document;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import no.kith.xmlstds.msghead._2006_05_24.RefDoc;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NaWebService implements NAWeb {

    @Resource
    WebServiceContext context;

    @Override
    public AppRec naWebServiceVerify(MV parameters) {
        var appRec = new AppRec();
        appRec.setDokument(parameters.getDokument());
        return appRec;
    }

    @Override
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {
        var m9Na2 = new M9Na2();
        m9Na2.setDokument(parameters.getDokument());
        return m9Na2;

//        var token = (String) context.getMessageContext().get("auth");
//        try {
//            var jwt = JWTParser.parse(token);
//            var header = jwt.getHeader();
//            var claims = jwt.getJWTClaimsSet();
//            System.out.println(header);
//            System.out.println(claims);
//
//            var certsEndpoint = "http://localhost:8080/realms/realm1/protocol/openid-connect/certs";
//            Issuer issuer = new Issuer("http://localhost:8080/realms/realm1");
//            OIDCProviderMetadata opMetadata = OIDCProviderMetadata.resolve(
//                    issuer,
//                    1000,
//                    1000);
//
//            var uri = opMetadata.getJWKSetURI();
//            var json = stream(uri.toURL());
//            var jwkSet = JWKSet.parse(json);
//            var keys = jwkSet.getKeys();
//
//            var rsaKey = keys.get(1).toRSAKey().toRSAPublicKey();
//            var verifier = new RSASSAVerifier(rsaKey);
//            var signedJwt = SignedJWT.parse(token);
//
//            signedJwt.verify(verifier);
//            System.out.println("Verified!");
//
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        } catch (IOException | GeneralException | JOSEException e) {
//        }


//        MsgHead msgHead;
//        var byteDokument = (byte[]) parameters.getDokument();
//        try {
//
//            JAXBContext jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA1.class);
//            var um = jaxbContext.createUnmarshaller();
//            msgHead = (MsgHead) um.unmarshal(new StringReader(new String(byteDokument)));
//
//            var vedleggI = msgHead.getDocument().listIterator();
//            vedleggI.next();
//
//            while (vedleggI.hasNext()) {
//                var vedlegg = vedleggI.next();
//                var currentDocument = vedlegg.getRefDoc().getContent().getAny().get(0);
//                //It is implied that any attachment on ERM9NA1 or ERM9NA3 is a SAML token -
//                // and there should be only one attachment
//                var base64Container = (Element) currentDocument;
//                var encodedValue = base64Container.getTextContent();
//                var decodedValue = new String(Base64.getDecoder().decode(encodedValue));
//                //var samlAssertion = stringToSAMLResponse(decodedValue);
//
//            }
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        var m9na2 = M9NA2.m9NA2Builder()
//                .withAntall(0)
//                .build();
//
//        var m9na2Document = new M9Na2();
//        String byteDocument;
//        try {
//            var jaxbContext = JAXBContext.newInstance(M9NA2.class, MsgHead.class);
//            var marshaller = jaxbContext.createMarshaller();
//            var stringWriter = new StringWriter();
//            marshaller.marshal(m9na2, stringWriter);
//            byteDocument = stringWriter.toString();
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//        m9na2Document.setDokument(byteDocument.getBytes(StandardCharsets.UTF_8));
//        return m9na2Document;
    }

    @Override
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
//        var m9na4 = new M9Na4();
//        m9na4.setDokument(parameters.getDokument());
//        return m9na4;

        var byteDokument = (byte[]) parameters.getDokument();
        MsgHead m9na3Dokument = null;
        try {
            var jaxbContext = JAXBContext.newInstance(M9NA3.class, MsgHead.class);
            var um = jaxbContext.createUnmarshaller();
            m9na3Dokument = (MsgHead) um.unmarshal(new ByteArrayInputStream(byteDokument));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
//
        M9NA3 m9na3 = (M9NA3) m9na3Dokument.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        var m9na4 = new M9Na4();
        var m9na4Dokument = M9NA4.m9NA4Builder()
                .withReseptId(m9na3.getReseptId())
                .withStatus(CS.CSBuilder().withV("").withDn("").build())
                .build();

        List<Document> documentList = new ArrayList<>();
        documentList.add(Document.documentBuilder()
                .withRefDoc(RefDoc.refDocBuilder()
                        .withMsgType(no.kith.xmlstds.msghead._2006_05_24.CS.CSBuilder().withV("XML").withDn("XML-instans").build())
                        .withContent(RefDoc.Content.contentBuilder()
                                .addAny(m9na4Dokument)
                                .build())
                        .build())
                .build());
        var msgHead = MsgHeadBuilder.buildResponseMessageHead(m9na3Dokument, MessageType.M9NA4.getCS(), documentList);

        try {
            var sw = new StringWriter();
            var jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA4.class);
            var m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(msgHead, sw);
            m9na4.setDokument(sw.toString().getBytes(StandardCharsets.UTF_8));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return m9na4;
    }


//    public static XMLObject stringToSAMLResponse(String samlResponse) throws Exception {
//        InitializationService.initialize();
//        ParserPool parser = XMLObjectProviderRegistrySupport.getParserPool();
//        StringReader reader = new StringReader(samlResponse);
//
//        assert parser != null;
//        Document doc = parser.parse(reader);
//        Element samlElement = doc.getDocumentElement();
//
//
//        UnmarshallerFactory unmarshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
//        Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(samlElement);
//        if (unmarshaller == null) {
//            throw new Exception("Failed to unmarshal");
//        }
//        return unmarshaller.unmarshall(samlElement);
//    }
}
