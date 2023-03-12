package io.github.mjhaugsdal.eresept.fest;

import no.kith.xmlstds.eresept.m30._2014_12_01.FEST;
import no.slv._201410325.FestService251;
import no.slv._201410325.FilterEnum;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.addressing.WSAddressingFeature;

import java.io.IOException;
import java.util.LinkedList;

public class FestServiceClient {

    FEST hentFest(String festAddress, FilterEnum filterEnum) throws IOException {
        var factory = new JaxWsProxyFactoryBean();
        factory.setAddress(festAddress);
        factory.setServiceClass(FestService251.class);
        factory.setBindingId("http://www.w3.org/2003/05/soap/bindings/HTTP/");//soap 1.2

        var features = new LinkedList<Feature>();
        features.add(new WSAddressingFeature());
        factory.setFeatures(features);

        var client = (FestService251) factory.create();

        /* Filter
         * Rekvirent (legemidler til humant bruk, samt næringsmidler og medisinsk utstyr)
         * Bandasjist (næringsmidler og medisinsk utstyr)
         * Veterinær (legemidler til veterinært bruk)
         * Farmalogg
         * Institusjon (til sykehus, kun versjon 2.5.1)
         */
        var m30 = client.getM30(filterEnum, null);
        return m30.getM30Message();
    }
}
