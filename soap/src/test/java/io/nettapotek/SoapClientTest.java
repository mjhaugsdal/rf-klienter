package io.nettapotek;

import no.ergo.reseptformidleren.webservices.na.*;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SoapClientTest {

    NAWeb client;

    @BeforeClass
    void setup() {
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
    void test() throws JAXBException {

        M9Na1 m9Na1 = new M9Na1();
        m9Na1.setDokument("<m9na1>test</m9na1>".getBytes(StandardCharsets.UTF_8));
        try {
            client.naWebServiceM9Na1(m9Na1);
        } catch (AppRecFault_Exception e) {
            var apprecDocumentBytes = (byte[]) e.getFaultInfo().getDokument();
            var jaxbContext = JAXBContext.newInstance(AppRec.class);
            var um = jaxbContext.createUnmarshaller();


            var doc = (no.kith.xmlstds.apprec._2004_11_21.AppRec) um.unmarshal(new StringReader(new String(apprecDocumentBytes, StandardCharsets.UTF_8)));
            throw new RuntimeException(e);
        }
    }

}
