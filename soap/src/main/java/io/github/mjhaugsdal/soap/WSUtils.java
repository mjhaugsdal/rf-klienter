package io.github.mjhaugsdal.soap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.WSS4JConstants;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import java.util.HashMap;
import java.util.Map;

public class WSUtils {

    public static void setupWSSEClient(JaxWsProxyFactoryBean bean, boolean encryption, boolean signature) throws WSSecurityException {
        String actions = " ";
        if (encryption) {
            actions = WSHandlerConstants.ENCRYPTION + " ";
        }
        if (signature) {
            actions += WSHandlerConstants.SIGNATURE;
        }
        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "client/client-sign-in.properties");
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "client/client-enc-in.properties");
        inProps.put(WSHandlerConstants.ACTION, actions);
        inProps.put(WSHandlerConstants.SIGNATURE_USER, "server"); //alias of signing certificate (public key)
        inProps.put(WSHandlerConstants.ENCRYPTION_USER, "client"); //alias of decryption certificate (private key)
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        var wssIn = new WSS4JInInterceptor(inProps);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "client/client-sign-out.properties");
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "client/client-enc-out.properties");
        outProps.put(WSHandlerConstants.ACTION, actions);
        outProps.put(WSHandlerConstants.SIGNATURE_USER, "client"); //alias of client certificate (private key)
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, "server"); //alias of server certificate (public key)
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());
        outProps.put(WSHandlerConstants.ENC_KEY_ID, "DirectReference");
        outProps.put(WSHandlerConstants.SIG_KEY_ID, "SKIKeyIdentifier");
        outProps.put(WSHandlerConstants.INCLUDE_SIGNATURE_TOKEN, "true");

        outProps.put(WSHandlerConstants.ENC_KEY_TRANSPORT, WSS4JConstants.KEYTRANSPORT_RSAOAEP_XENC11);
        outProps.put(WSHandlerConstants.ENC_SYM_ALGO, WSS4JConstants.AES_256_GCM);
        outProps.put(WSHandlerConstants.SIG_ALGO, WSS4JConstants.RSA_SHA512);
        outProps.put(WSHandlerConstants.SIG_DIGEST_ALGO, WSS4JConstants.SHA512);
        outProps.put(WSHandlerConstants.ENC_MGF_ALGO, WSS4JConstants.MGF_SHA512);
        outProps.put(WSHandlerConstants.ENC_DIGEST_ALGO, WSS4JConstants.SHA512);

        var wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);
    }
}
