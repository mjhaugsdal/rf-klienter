package io.nettapotek.soap;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.PlainObject;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.GeneralException;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.id.Issuer;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import net.shibboleth.utilities.java.support.xml.ParserPool;
import no.ergo.reseptformidleren.webservices.na.*;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import org.apache.cxf.rs.security.jose.jwt.JwtToken;
import org.opensaml.core.config.InitializationService;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.UUID;

public class NaWebService implements NAWeb {

    @Resource
    WebServiceContext context;

    @Override
    public AppRec naWebServiceVerify(MV parameters) {
        return new AppRec();
    }

    @Override
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {

        var token = (String) context.getMessageContext().get("auth");
        try {
            var jwt = JWTParser.parse(token);
            var header = jwt.getHeader();
            var claims = jwt.getJWTClaimsSet();
            System.out.println(header);
            System.out.println(claims);

            var certsEndpoint = "http://localhost:8080/realms/realm1/protocol/openid-connect/certs";
            Issuer issuer = new Issuer("http://localhost:8080/realms/realm1");
            OIDCProviderMetadata opMetadata = OIDCProviderMetadata.resolve(
                    issuer,
                    1000,
                    1000);

            var uri = opMetadata.getJWKSetURI();
            var json = stream(uri.toURL());
            var jwkSet = JWKSet.parse(json);
            var keys = jwkSet.getKeys();

            var rsaKey = keys.get(1).toRSAKey().toRSAPublicKey();
            var verifier = new RSASSAVerifier(rsaKey);
            var signedJwt = SignedJWT.parse(token);

            signedJwt.verify(verifier);
            System.out.println("Verified!");

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        } catch (IOException | GeneralException | JOSEException e) {
            //throw new RuntimeException(e);
        }

        var byteDokument = (byte[]) parameters.getDokument();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA1.class);
            var um = jaxbContext.createUnmarshaller();
            var msgHead = (MsgHead) um.unmarshal(new StringReader(new String(byteDokument)));

            var vedleggI = msgHead.getDocument().listIterator();
            vedleggI.next();

            while (vedleggI.hasNext()) {
                var vedlegg = vedleggI.next();
                var currentDocument = vedlegg.getRefDoc().getContent().getAny().get(0);
                //It is implied that any attachment on ERM9NA1 or ERM9NA3 is a SAML token -
                // and there should be only one attachment
                var base64Container = (Element) currentDocument;
                var encodedValue = base64Container.getTextContent();
                var decodedValue = new String(Base64.getDecoder().decode(encodedValue));
                var samlAssertion = stringToSAMLResponse(decodedValue);

            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        var m9na2 = new M9NA2();
        m9na2.setAntall(0);
        var m9na2Document = new M9Na2();
        String byteDocument;
        try {
            var jaxbContext = JAXBContext.newInstance(M9NA2.class);
            var marshaller = jaxbContext.createMarshaller();
            var stringWriter = new StringWriter();
            marshaller.marshal(m9na2, stringWriter);
            byteDocument = stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        m9na2Document.setDokument(byteDocument.getBytes(StandardCharsets.UTF_8));
        return m9na2Document;
    }

    @Override
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        var appRec = new no.kith.xmlstds.apprec._2004_11_21.AppRec();
        appRec.setId(UUID.randomUUID().toString());

        AppRecFault appRecFault = new AppRecFault();
        String byteDocument;

        try {
            var jaxbContext = JAXBContext.newInstance(no.kith.xmlstds.apprec._2004_11_21.AppRec.class);
            var marshaller = jaxbContext.createMarshaller();
            var stringWriter = new StringWriter();
            marshaller.marshal(appRec, stringWriter);
            byteDocument = stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        appRecFault.setDokument(byteDocument.getBytes(StandardCharsets.UTF_8));

        throw new AppRecFault_Exception("", appRecFault);
    }


    public static XMLObject stringToSAMLResponse(String samlResponse) throws Exception {
        InitializationService.initialize();
        ParserPool parser = XMLObjectProviderRegistrySupport.getParserPool();
        StringReader reader = new StringReader(samlResponse);

        assert parser != null;
        Document doc = parser.parse(reader);
        Element samlElement = doc.getDocumentElement();


        UnmarshallerFactory unmarshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
        Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(samlElement);
        if (unmarshaller == null) {
            throw new Exception("Failed to unmarshal");
        }
        return unmarshaller.unmarshall(samlElement);
    }

    public static String stream(URL url) {
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
