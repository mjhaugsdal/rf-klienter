package io.nettapotek;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import java.util.HashMap;
import java.util.Map;

import static org.apache.wss4j.common.ConfigurationConstants.USE_REQ_SIG_CERT;

public class WSUtils {

    public static void setupWSSEServer(JaxWsServerFactoryBean bean) {

        Map<String,Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "properties.properties");
        inProps.put(WSHandlerConstants.ENC_PROP_FILE, "properties.properties");
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        inProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of signing certificate (private key)
        inProps.put(WSHandlerConstants.ENCRYPTION_USER, "server"); //alias of encryption certificate (public key)
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClass.class.getName());
        //inProps.put(WSHandlerConstants.ENC_KEY_ID, "X509KeyIdentifier");

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        Map<String,Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "properties.properties");
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "properties.properties");
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        outProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of client certificate (private key)
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, USE_REQ_SIG_CERT); //alias of server certificate (public key)
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClass.class.getName());

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);
    }

    public static void setupWSSEClient(JaxWsProxyFactoryBean bean) {

        Map<String,Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "properties.properties");
        inProps.put(WSHandlerConstants.ENC_PROP_FILE, "properties.properties");
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        inProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of signing certificate (private key)
        inProps.put(WSHandlerConstants.ENCRYPTION_USER, "server"); //alias of encryption certificate (public key)
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClass.class.getName());

        var wssIn = new WSS4JInInterceptor(inProps);

        Map<String,Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "properties.properties");
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "properties.properties");
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        outProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of client certificate (private key)
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, "server"); //alias of server certificate (public key)
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClass.class.getName());
        outProps.put(WSHandlerConstants.ENC_KEY_ID, "SKIKeyIdentifier");
        outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
        outProps.put(WSHandlerConstants.INCLUDE_SIGNATURE_TOKEN, "true");

        var wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);
    }
}
