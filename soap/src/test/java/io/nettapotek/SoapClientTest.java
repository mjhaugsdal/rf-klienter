package io.nettapotek;

import no.ergo.reseptformidleren.webservices.na.*;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SoapClientTest {

    NAWeb client;

    @BeforeClass
    void setup() throws WSSecurityException {
        var factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setServiceBean(new NaWebService());
        factoryBean.setAddress("http://localhost:8888/NA"); //TODO portkonfig

        List<Feature> features = new ArrayList<>();
        var loggingFeature = new LoggingFeature();
        features.add(loggingFeature);
        factoryBean.setFeatures(features);

        WSUtils.setupWSSEServer(factoryBean);
        factoryBean.create();

        var jaxWsClientFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsClientFactoryBean.setServiceClass(NAWeb.class);
        jaxWsClientFactoryBean.setAddress("http://localhost:8888/NA");
        WSUtils.setupWSSEClient(jaxWsClientFactoryBean);
        client = (NAWeb) jaxWsClientFactoryBean.create();
    }

    @Test
    void testOKResponse() throws Exception {

        M9Na1 m9Na1 = new M9Na1();
        //m9Na1.setDokument("<m9na1>test</m9na1>".getBytes(StandardCharsets.UTF_8));
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
