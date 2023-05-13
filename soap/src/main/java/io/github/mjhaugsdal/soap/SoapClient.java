package io.github.mjhaugsdal.soap;


import io.nettapotek.kith.M9NA1Factory;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.wss4j.common.ext.WSSecurityException;


public class SoapClient {

    static NAWeb client;
    Client httpClient;

    public static void main(String[] args) throws Exception {

        var jaxWsClientFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsClientFactoryBean.setServiceClass(NAWeb.class);
        jaxWsClientFactoryBean.setAddress("http://rf.test3-na-int.reseptformidleren.net/NA/NAWebServiceSoapHttpPort");
        WSUtils.setupWSSEClient(jaxWsClientFactoryBean);

        client = (NAWeb) jaxWsClientFactoryBean.create();
        var m9na1Envelope = new M9Na1();
        m9na1Envelope.setDokument(M9NA1Factory.buildM9na1());

        try {
            client.naWebServiceM9Na1(m9na1Envelope);
        } catch (AppRecFault_Exception appRecFaultException) {
            var dokument = appRecFaultException.getFaultInfo().getDokument();
        }
    }
}
