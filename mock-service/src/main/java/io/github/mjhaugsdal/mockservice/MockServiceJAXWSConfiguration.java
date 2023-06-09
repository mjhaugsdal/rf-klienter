package io.github.mjhaugsdal.mockservice;

import io.github.mjhaugsdal.soap.MyCallbackClass;
import io.github.mjhaugsdal.soap.NaWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.wss4j.common.ConfigurationConstants.USE_REQ_SIG_CERT;

@Configuration
public class MockServiceJAXWSConfiguration {

    @Autowired
    private Bus bus;

    @Autowired
    private MetricsProvider metricsProvider;

    @Bean
    public Server endpoint() {

        var bean = new JaxWsServerFactoryBean();
        bean.setServiceBean(new NaWebService());
        bean.setAddress("http://localhost:8881/Soap"); //TODO portkonfig

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "server/server-sign-in.properties");
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "server/server-enc-in.properties");
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        inProps.put(WSHandlerConstants.SIGNATURE_USER, "client"); //alias of signing certificate (public key)
        inProps.put(WSHandlerConstants.ENCRYPTION_USER, "server"); //alias of decryption certificate (private key)
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClass.class.getName());

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "server/server-sign-out.properties");
        //outProps.put(WSHandlerConstants.ENC_PROP_FILE, "server/server-enc-in.properties"); //Not necessary when USE_REQ_SIG_CERT is in use
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        outProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of server certificate (private key)
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, USE_REQ_SIG_CERT); //alias of client certificate (public key)
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClass.class.getName());

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getOutFaultInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);

        List<Feature> features = new ArrayList<>();
        features.add(new org.apache.cxf.ext.logging.LoggingFeature());
        features.add(new MetricsFeature());
        bean.setFeatures(features);


        return bean.create();
//        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
//        jaxWsServerFactoryBean

//        EndpointImpl endpoint = new EndpointImpl(bus, new NaWebService(),  null, null, new MetricsFeature[] {
//                new MetricsFeature(metricsProvider)
//        });
//        endpoint.publish("http://localhost:8881/Soap");
//        return endpoint;
    }

}
