package io.nettapotek;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.*;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientInformation;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientInformationResponse;
import io.nettapotek.interceptor.AuthInterceptor;
import no.ergo.reseptformidleren.webservices.na.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class SoapClientTest {

    NAWeb client;

    Client httpClient;

    @BeforeClass
    void setup() throws WSSecurityException {
        var factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setServiceBean(new NaWebService());
        factoryBean.setAddress("http://localhost:8888/NA"); //TODO portkonfig

        List<Feature> features = new ArrayList<>();
        var loggingFeature = new LoggingFeature();
        features.add(loggingFeature);
        factoryBean.setFeatures(features);
        factoryBean.getInInterceptors().add(new AuthInterceptor());
        WSUtils.setupWSSEServer(factoryBean);
        factoryBean.create();

        var jaxWsClientFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsClientFactoryBean.setServiceClass(NAWeb.class);
        jaxWsClientFactoryBean.setAddress("http://localhost:8888/NA");
        WSUtils.setupWSSEClient(jaxWsClientFactoryBean);
        client = (NAWeb) jaxWsClientFactoryBean.create();
        httpClient = ClientProxy.getClient(client);

        //var jwt = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfcVJDUTVQSjJ0NlNHWHZkVnNmQ0YwQU9lSEpsLXNWYVhDOGZBMU4yaHo4In0.eyJleHAiOjE2NzM1MzIyMTUsImlhdCI6MTY3MzUzMTkxNSwiYXV0aF90aW1lIjoxNjczNTMxNTkwLCJqdGkiOiI5MzkyZWM0MS05NGQ0LTQxMzUtOGJmMS0yNTM3MjExYjQzZmYiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL3JlYWxtMSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2OTZmMjc2OC0xMjQwLTRjMWQtODhhNi1jYzk3YTE0MTVkNDYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjbGllbnQxIiwic2Vzc2lvbl9zdGF0ZSI6ImI5OWE4NmEyLTNkY2QtNDQzMi1iZGVkLThlNjJmMWI1M2E5ZiIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4ODg4LyJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXJlYWxtMSIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsInNpZCI6ImI5OWE4NmEyLTNkY2QtNDQzMi1iZGVkLThlNjJmMWI1M2E5ZiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiIiwiZmFtaWx5X25hbWUiOiIifQ.d-NyFn_fgKuzVSLDgErbsaTM3n01YZJlHIqDC2Vsih4SadjfTNjW2WBmFynQwgn5lyen3i96fAXu5teqNbmezYqx9qpe_ugMuS8Ut-gbvQynIVvXfUuQXh3zm0XCcVnmSu_fz_Lc2ThmyDIrK04E2qyKp0Cqt4iNPBQTGfRxL9fGB1j7CZ6XL89dhObqXLaShZ2xjp69lUWa6PUradennNpqMDGOMlfL-eZsa5JbJw31UQ_Ht2VK4QErnM6dfmiwyJMveLq7AupzdxN0OCuL78g0fxZAXCyyPUxG6CZ0OIFVy3s2vVQt9mJuNJQaJ6AvCvZSXYQVLUw869cWRPUiTQ";
        //httpClient.getRequestContext().put(AUTHORIZATION + ":", "Bearer" + jwt);

    }

    @Test
    void testOKResponse() throws Exception {

        var jwt = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfcVJDUTVQSjJ0NlNHWHZkVnNmQ0YwQU9lSEpsLXNWYVhDOGZBMU4yaHo4In0.eyJleHAiOjE2NzM1MzIyMTUsImlhdCI6MTY3MzUzMTkxNSwiYXV0aF90aW1lIjoxNjczNTMxNTkwLCJqdGkiOiI5MzkyZWM0MS05NGQ0LTQxMzUtOGJmMS0yNTM3MjExYjQzZmYiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL3JlYWxtMSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2OTZmMjc2OC0xMjQwLTRjMWQtODhhNi1jYzk3YTE0MTVkNDYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjbGllbnQxIiwic2Vzc2lvbl9zdGF0ZSI6ImI5OWE4NmEyLTNkY2QtNDQzMi1iZGVkLThlNjJmMWI1M2E5ZiIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4ODg4LyJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXJlYWxtMSIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsInNpZCI6ImI5OWE4NmEyLTNkY2QtNDQzMi1iZGVkLThlNjJmMWI1M2E5ZiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiIiwiZmFtaWx5X25hbWUiOiIifQ.d-NyFn_fgKuzVSLDgErbsaTM3n01YZJlHIqDC2Vsih4SadjfTNjW2WBmFynQwgn5lyen3i96fAXu5teqNbmezYqx9qpe_ugMuS8Ut-gbvQynIVvXfUuQXh3zm0XCcVnmSu_fz_Lc2ThmyDIrK04E2qyKp0Cqt4iNPBQTGfRxL9fGB1j7CZ6XL89dhObqXLaShZ2xjp69lUWa6PUradennNpqMDGOMlfL-eZsa5JbJw31UQ_Ht2VK4QErnM6dfmiwyJMveLq7AupzdxN0OCuL78g0fxZAXCyyPUxG6CZ0OIFVy3s2vVQt9mJuNJQaJ6AvCvZSXYQVLUw869cWRPUiTQ";
        Map<String, List<String>> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + jwt));
        httpClient.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);

        M9Na1 m9Na1 = new M9Na1();
        M9na m9na = new M9na();
        m9Na1.setDokument(m9na.getM9na1().getBytes(StandardCharsets.UTF_8));
        try {
            var response = client.naWebServiceM9Na1(m9Na1);

        } catch (AppRecFault_Exception e) {
            var apprecDocumentBytes = (byte[]) e.getFaultInfo().getDokument();
            var jaxbContext = JAXBContext.newInstance(AppRec.class);
            var um = jaxbContext.createUnmarshaller();


            throw new RuntimeException(e);
        }
    }

    @Test
    void testNegativeResponse() throws JAXBException {

        M9Na3 m9Na3= new M9Na3();
        m9Na3.setDokument("<m9na3>test</m9na3>".getBytes(StandardCharsets.UTF_8));
        try {
            var response = client.naWebServiceM9Na3(m9Na3);

        } catch (AppRecFault_Exception e) {
            var apprecDocumentBytes = (byte[]) e.getFaultInfo().getDokument();
            var jaxbContext = JAXBContext.newInstance(no.kith.xmlstds.apprec._2004_11_21.AppRec.class);
            var um = jaxbContext.createUnmarshaller();

            no.kith.xmlstds.apprec._2004_11_21.AppRec appRec = (no.kith.xmlstds.apprec._2004_11_21.AppRec) um.unmarshal(new StringReader(new String(apprecDocumentBytes)));

            Assert.assertNotNull(appRec.getId());
        }
    }

}
