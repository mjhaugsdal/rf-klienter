package io.github.mjhaugsdal.eresept.fest.configuration;

import jakarta.xml.ws.soap.SOAPBinding;
import no.slv._201410325.FestService251;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

@Configuration
public class FestConfiguration {

    @Value("${soap.address}")
    String address;

    @Bean
    public FestService251 festService251() {
        var factory = new JaxWsProxyFactoryBean();
        factory.setAddress(address);
        factory.setServiceClass(FestService251.class);
        factory.setBindingId(SOAPBinding.SOAP12HTTP_BINDING);//soap 1.2

        var features = new LinkedList<Feature>();
        features.add(new WSAddressingFeature());
        factory.setFeatures(features);

        return (FestService251) factory.create();
    }
}
