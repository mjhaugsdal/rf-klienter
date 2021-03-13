package im.haugsdal.playground.nettapotek.gateway;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Iterator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import im.haugsdal.playground.nettapotek.handler.Eksempler;
import im.haugsdal.playground.nettapotek.m9na1._2016_06_06.M9Na1Handler;
import no.kith.xmlstds.apprec._2004_11_21.AppRec;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.msghead._2006_05_24.Document;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import no.kith.xmlstds.msghead._2006_05_24.RefDoc;
import org.apache.cxf.helpers.HttpHeaderHelper;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;


@Path("/nettapotek")
@SecuritySchemes(
        @SecurityScheme(
                name = "JwtToken",
                scheme = "bearer",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT"
        )
)
@SecurityRequirement(
        name = "JwtToken"
)
public class NettapotekService {

    private static final Logger logger = LoggerFactory.getLogger(NettapotekService.class.getName());


    @Context
    HttpHeaders httpHeaders;

    @Context
    MessageContext jaxRsContext;

    @POST
    @Path("/m9na1")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    @Operation(
            operationId = "subscribe",
            description = "Forespørsel om reseptliste fra nettkunde"
    )

    @SecurityRequirement(name = "JwtToken")
    public Response m9na1(

            @RequestBody(
            required = true,
            content = @Content(
                    examples = {
                            @ExampleObject(
                                    name = "Eksempel på M9NA1",
                                    value = Eksempler.M9NA1)
                    }
            )
    ) MsgHead dokument) {

        try {

            //TODO handle kryptert body?

            //http://cxf.apache.org/docs/jax-rs-jose.html#JAX-RSJOSE-JWECompact
            /*
            //TODO eksempel for decryptering med AES256, ser ut som 3DES ikke støttes, men det går bare fint det

            // Load Private RSA Key from Java JKS Store
            PrivateKey privateRsaKey =
                CryptoUtils.loadPrivateKey(keyStoreLocation, keyStorePassword, privateKeyPassword, keyAlias, KeyStore.getDefaultType());

            JweDecryptionProvider decryptor = JweUtils.createJweDecryptionProvider(jwkPrivateRsaKey, ContentAlgorithm.A256GCM);
            String decryptedText = decryption.decrypt(jweContent).getContentText();
            assertEquals(content, decryptedText);

             */

            /*
            * TODO alternativt kan også dekryptering av body være 100% cxf, se https://cxf.apache.org/docs/jax-rs-xml-security.html
            * TODO under "XML Encryption" //https://cxf.apache.org/docs/jax-rs-xml-security.html
            *
            *
            * */

            //TODO signatur?

            //https://cxf.apache.org/docs/jax-rs-xml-security.html
            //TODO Ser ut som sjekk av xml signatur kan gjøres 100% med cxf + spring beans,

            /*
            * <bean id="serviceBean" class="org.apache.cxf.systest.jaxrs.security.BookStore"/>
                <bean id="xmlSigHandler" class="org.apache.cxf.rs.security.xml.XmlSigInHandler"/>
                <bean id="xmlSigOutHandler" class="org.apache.cxf.rs.security.xml.XmlSigOutInterceptor"/>

                <jaxrs:server address="/xmlsig">
                    <jaxrs:serviceBeans>
                      <ref bean="serviceBean"/>
                    </jaxrs:serviceBeans>
                    <!--
                       Required for validating the in signature and removing it from the payload.
                       It also persists the signature on the current Message which can be disabled.
                    -->
                    <jaxrs:providers>
                      <ref bean="xmlSigHandler"/>
                    </jaxrs:providers>
                    <!--
                       Required for adding a new signature to the outbound payload
                    -->
                    <jaxrs:outInterceptors>
                          <ref bean="xmlSigOutHandler"/>
                    </jaxrs:outInterceptors>

                    <jaxrs:properties>
                          <entry key="security.callback-handler"
                                  value="org.apache.cxf.systest.jaxrs.security.saml.KeystorePasswordCallback"/>
                          <entry key="security.signature.properties"
                                  value="org/apache/cxf/systest/jaxrs/security/alice.properties"/>
                    </jaxrs:properties>
                </jaxrs:server>
            *
            *
            *
            * */

            //Litt logikk for å hente auth token fra http header
            String auth = httpHeaders.getRequestHeaders().getFirst(HttpHeaderHelper.AUTHORIZATION);
            MultivaluedMap<String, String> auth2 = httpHeaders.getRequestHeaders();
            System.out.println(auth2.size());
            Iterator<String> it = auth2.keySet().iterator();
            while(it.hasNext()) {
                String theKey = it.next();
                System.out.print(theKey + " ");
                System.out.println(auth2.get(theKey));

            }
            System.out.println(auth);



            // continue with header and payload extraction...
            String[] parts = auth.split(" ");
            String strToken = parts[1];

            // Create a JWT processor for the access tokens
            ConfigurableJWTProcessor<SecurityContext> jwtProcessor =
                    new DefaultJWTProcessor<>();
            jwtProcessor.setJWSTypeVerifier(
                    new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("JWT")));

            // The public RSA keys to validate the signatures will be sourced from the
            // OAuth 2.0 server's JWK set, published at a well-known URL. The RemoteJWKSet
            // object caches the retrieved keys to speed up subsequent look-ups and can
            // also handle key-rollover

            //TODO her skal well-known URL være, lookups ble cachet
            /*JWKSource<SecurityContext> keySource =
                new RemoteJWKSet<>(new URL("https://demo.c2id.com/c2id/jwks.json"));*/

            // The expected JWS algorithm of the access tokens (agreed out-of-band)
            JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;



            // Configure the JWT processor with a key selector to feed matching public
            // RSA keys sourced from the JWK set URL

            //JWSKeySelector<SecurityContext> keySelector =
            // new JWSVerificationKeySelector<>(expectedJWSAlg, keySource);
            // jwtProcessor.setJWSKeySelector(keySelector);

            // Set the required JWT claims for access tokens issued by the Connect2id
            // server, may differ with other servers

            //TODO her setter vi hvilke claims vi skal validere..
            /*jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier(
                    new JWTClaimsSet.Builder().issuer("https://demo.c2id.com/c2id").build(),
                    new HashSet<>(Arrays.asList("sub", "iat", "exp", "scp", "cid", "jti"))));*/

            // Process the token

            //TODO her prosseser vi selve tokenet
            /*
            SecurityContext ctx = null; // optional context parameter, not required here
            JWTClaimsSet claimsSet = jwtProcessor.process(strToken, ctx);
            */

            // Print out the token claims set
            //System.out.println(claimsSet.toJSONObject());

            //If jwtToken OK?
            //return Handle(Payload)

            //If jwtToken not ok?
            //throw custom exception?(negative AppRec/AppRecFault) or specialized http status, like 401 "Not Authorized"?
            //
            // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401

            M9Na1Handler m9Na1Handler = new M9Na1Handler();

            Object handlerResponse = m9Na1Handler.handleMessage(dokument);


            //TODO bygg hodemelding basert på innkomne melding
            MsgHead mhd = new MsgHead();



            Document document = new Document();
            RefDoc refDoc = new RefDoc();
            document.setRefDoc(refDoc);
            RefDoc.Content content = new RefDoc.Content();
            refDoc.setContent(content);
            content.getAny().add(handlerResponse);

            mhd.getDocument().add(document);
            //mhd.getDocument().get(0).getRefDoc().getContent().getAny().add(handlerResponse);

            JAXBContext jaxbContext = JAXBContext.newInstance(MsgHead.class.getPackage().getName() + ":" + M9NA2.class.getPackage().getName());

            //Marshaller marshaller = jaxbContext.createMarshaller();
            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            m.marshal(mhd, sw);

            String result = sw.toString(); //TODO dette er fordi innebygd jaxbcontext sliter med KITH's blanding av xml schemas
            System.out.println(result);

/*
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();

            marshaller.marshal( mhd, doc );*/


            return Response.status(Response.Status.OK)
                .entity(result).build();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            AppRec appRec = new AppRec();//= lagAppRec(el, dokument);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(appRec).build(); //Returnerer apprec med X99 OG http status 406, som et eksempel
        }
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    private AppRec handle(MsgHead msgHead) {

        String msgType = getMessageType(msgHead);
        assert msgType != null;

        if (msgType.equalsIgnoreCase("ERM9NA1")) {
            return process(msgHead);
        }
        else if(msgType.equalsIgnoreCase("ERM9NA3")){
            return process(msgHead);
        }
        else {
            return null;
        }
    }

    private String getMessageType(MsgHead msgHead) {

        try{
            return msgHead.getMsgInfo().getType().getV();

        } catch (NullPointerException ex) {
            //Throw custom exception, V feltet mangler.. skal lage negativ apprec med feilkode xx.. 
            throw new Fault(ex);
        }
    }

    private AppRec process(MsgHead msgHead) {

        Node node = (Node) msgHead.getDocument().get(0).getRefDoc().getContent().getAny().get(0);
        Object object = null;

        try {
            JAXBContext context = JAXBContext.newInstance(M9NA1.class.getPackage().getName());
            Unmarshaller parser = context.createUnmarshaller();
            object = parser.unmarshal(node);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if (object instanceof M9NA1) {
            M9NA1 m9NA1 = (M9NA1) object;

            String id = m9NA1.getIdKunde().getId();
            System.out.println(id);

        }
        if (object instanceof M9NA3) {
            M9NA3 m9NA3 = (M9NA3) object;

        }

        //TODO return proper apprec
        AppRec appRec = new AppRec();
        return appRec;

        //return appRecBuilder.makeAppRec(msgHead, el);
    }

    @POST
    @Path("/m9na3")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    @Operation(
        operationId = "subscribe",
        description = "Forespørsel om utvidet informasjon på resept fra nettkunde"
    )

    @SecurityRequirement(name = "JwtToken")
    public AppRec m9na3(

        @RequestBody(
            required = true,
            content = @Content(
                examples = {
                    @ExampleObject(
                        name = "Eksempel på M9NA3",
                        value = Eksempler.M9NA3)
                }
            )
        ) MsgHead dokument) throws JAXBException, MalformedURLException, ParseException, JOSEException, BadJOSEException {

        try{
            String auth = httpHeaders.getRequestHeaders().getFirst(HttpHeaderHelper.AUTHORIZATION);

            MultivaluedMap<String, String> auth2 = httpHeaders.getRequestHeaders();
            System.out.println(auth2.size());
            Iterator<String> it = auth2.keySet().iterator();
            while(it.hasNext()) {
                String theKey = it.next();
                System.out.print(theKey + " ");
                System.out.println(auth2.get(theKey));

            }
            System.out.println(auth);



            // continue with header and payload extraction...
            String[] parts = auth.split(" ");
            String strToken = parts[1];


            // Create a JWT processor for the access tokens
            ConfigurableJWTProcessor<SecurityContext> jwtProcessor =
                new DefaultJWTProcessor<>();
            jwtProcessor.setJWSTypeVerifier(
                new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("JWT")));

            // The public RSA keys to validate the signatures will be sourced from the
            // OAuth 2.0 server's JWK set, published at a well-known URL. The RemoteJWKSet
            // object caches the retrieved keys to speed up subsequent look-ups and can
            // also handle key-rollover
            //JWKSource<SecurityContext> keySource =
            //        new RemoteJWKSet<>(new URL("https://demo.c2id.com/c2id/jwks.json"));


            // The expected JWS algorithm of the access tokens (agreed out-of-band)
            //        JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;


            // Configure the JWT processor with a key selector to feed matching public
            // RSA keys sourced from the JWK set URL
            //        JWSKeySelector<SecurityContext> keySelector =
            //                new JWSVerificationKeySelector<>(expectedJWSAlg, keySource);

            //jwtProcessor.setJWSKeySelector(keySelector);



            // Set the required JWT claims for access tokens issued by the Connect2id
            // server, may differ with other servers
        /*jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier(
                new JWTClaimsSet.Builder().issuer("https://demo.c2id.com/c2id").build(),
                new HashSet<>(Arrays.asList("sub", "iat", "exp", "scp", "cid", "jti"))));*/

            // Process the token
       /* SecurityContext ctx = null; // optional context parameter, not required here
        JWTClaimsSet claimsSet = jwtProcessor.process(strToken, ctx);*/

            // Print out the token claims set
            //System.out.println(claimsSet.toJSONObject());


            //JaxbParser parser = new JaxbParser("no.kith.xmlstds");
            //JAXBContext jc = JAXBContext.newInstance("no.kith.xmlstds");
            //Marshaller marshaller = jc.createMarshaller();
            //marshaller.marshal(dokument);
            //String test = parser.marshall(dokument);

            //System.out.println(test);
        } catch (Exception ex) {

        }



        return handle(dokument);
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    @Operation(description = "hello world")
    public String hello(){
        return "Hello world!";
    }

}
