package io.github.mjhaugsdal.soap;

//import io.nettapotek.kith.M9na;
import io.github.mjhaugsdal.soap.interceptor.AuthInterceptor;
import io.github.mjhaugsdal.kith.xml.M9NA1Factory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import no.ergo.reseptformidleren.webservices.na.*;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.cxf.helpers.HttpHeaderHelper.AUTHORIZATION;


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
        headers.put(AUTHORIZATION, List.of("Bearer " + jwt));
        httpClient.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);

        var m9Na1 = new M9Na1();
        var m9na = new M9NA1Factory();

        m9Na1.setDokument(m9na.getM9na1().getBytes(StandardCharsets.UTF_8));

        try {
            var response = client.naWebServiceM9Na1(m9Na1);
            var jaxbContext = JAXBContext.newInstance(M9NA2.class);
            var byteDokument = (byte[]) response.getDokument();
            var m9Na2 = (M9NA2) jaxbContext.createUnmarshaller().unmarshal(new ByteArrayInputStream(byteDokument));
            var antall = m9Na2.getAntall(); //TODO m9na2 skal egentlig være fagmelding i en hodemelding
            Assert.assertEquals(antall, 0);
        } catch (AppRecFault_Exception e) {
            Assert.fail();
        }
    }

    @Test
    void testNegativeResponse() throws JAXBException {

        var jwt = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfcVJDUTVQSjJ0NlNHWHZkVnNmQ0YwQU9lSEpsLXNWYVhDOGZBMU4yaHo4In0.eyJleHAiOjE2NzM1MzIyMTUsImlhdCI6MTY3MzUzMTkxNSwiYXV0aF90aW1lIjoxNjczNTMxNTkwLCJqdGkiOiI5MzkyZWM0MS05NGQ0LTQxMzUtOGJmMS0yNTM3MjExYjQzZmYiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL3JlYWxtMSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2OTZmMjc2OC0xMjQwLTRjMWQtODhhNi1jYzk3YTE0MTVkNDYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjbGllbnQxIiwic2Vzc2lvbl9zdGF0ZSI6ImI5OWE4NmEyLTNkY2QtNDQzMi1iZGVkLThlNjJmMWI1M2E5ZiIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4ODg4LyJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXJlYWxtMSIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsInNpZCI6ImI5OWE4NmEyLTNkY2QtNDQzMi1iZGVkLThlNjJmMWI1M2E5ZiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiIiwiZmFtaWx5X25hbWUiOiIifQ.d-NyFn_fgKuzVSLDgErbsaTM3n01YZJlHIqDC2Vsih4SadjfTNjW2WBmFynQwgn5lyen3i96fAXu5teqNbmezYqx9qpe_ugMuS8Ut-gbvQynIVvXfUuQXh3zm0XCcVnmSu_fz_Lc2ThmyDIrK04E2qyKp0Cqt4iNPBQTGfRxL9fGB1j7CZ6XL89dhObqXLaShZ2xjp69lUWa6PUradennNpqMDGOMlfL-eZsa5JbJw31UQ_Ht2VK4QErnM6dfmiwyJMveLq7AupzdxN0OCuL78g0fxZAXCyyPUxG6CZ0OIFVy3s2vVQt9mJuNJQaJ6AvCvZSXYQVLUw869cWRPUiTQ";
        Map<String, List<String>> headers = new HashMap<>();
        headers.put(AUTHORIZATION, List.of("Bearer " + jwt));
        httpClient.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);


        M9Na3 m9Na3 = new M9Na3();

        JAXBContext jaxbContext = JAXBContext.newInstance(MsgHead.class, M9NA3.class);
        var um = jaxbContext.createUnmarshaller();
        var m = jaxbContext.createMarshaller();
        var is = M9NA2Factory.class.getClassLoader().getResourceAsStream("M9NA3_eks.xml");
        var msgHead = (MsgHead) um.unmarshal(is);
        var sw = new StringWriter();
        m.marshal(msgHead, sw);
        m9Na3.setDokument(sw.toString().getBytes(StandardCharsets.UTF_8));

        try {
            var response = client.naWebServiceM9Na3(m9Na3);

        } catch (AppRecFault_Exception e) {
            var apprecDocumentBytes = (byte[]) e.getFaultInfo().getDokument();
            jaxbContext = JAXBContext.newInstance(no.kith.xmlstds.apprec._2004_11_21.AppRec.class);
            um = jaxbContext.createUnmarshaller();

            //no.kith.xmlstds.apprec._2004_11_21.AppRec appRec = (no.kith.xmlstds.apprec._2004_11_21.AppRec) um.unmarshal(new StringReader(new String(apprecDocumentBytes)));
            no.kith.xmlstds.apprec._2004_11_21.AppRec appRec = (no.kith.xmlstds.apprec._2004_11_21.AppRec) um.unmarshal(new ByteArrayInputStream(apprecDocumentBytes));
            Assert.assertNotNull(appRec.getId());
        } catch (Exception ex) {
            ex.printStackTrace(); //unmarshaller won't work anymore
        }
    }

}
