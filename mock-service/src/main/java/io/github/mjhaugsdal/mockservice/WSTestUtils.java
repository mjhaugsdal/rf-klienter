package io.github.mjhaugsdal.mockservice;

import io.github.mjhaugsdal.soap.PasswordCallback;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import java.util.HashMap;
import java.util.Map;

import static org.apache.wss4j.common.ConfigurationConstants.USE_REQ_SIG_CERT;

public class WSTestUtils {

    public static void setupWSSEServer(JaxWsServerFactoryBean bean, boolean encryption, boolean signature) {

        String actions = " ";
        if (encryption) {
            actions = WSHandlerConstants.ENCRYPTION + " ";
        }
        if (signature) {
            actions += WSHandlerConstants.SIGNATURE;
        }

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "server/server-sign-in.properties");
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "server/server-enc-in.properties");
        inProps.put(WSHandlerConstants.ACTION, actions);
        inProps.put(WSHandlerConstants.SIGNATURE_USER, "client"); //alias of signing certificate (public key)
        inProps.put(WSHandlerConstants.ENCRYPTION_USER, "server"); //alias of decryption certificate (private key)
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "server/server-sign-out.properties");
        //outProps.put(ConfigurationConstants.ENC_PROP_FILE, "server/server-enc-in.properties"); //Not necessary when USE_REQ_SIG_CERT is in use
        outProps.put(WSHandlerConstants.ACTION, actions);
        outProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of server certificate (private key)
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, USE_REQ_SIG_CERT); //alias of client certificate (public key)
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getOutFaultInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);
    }
}
