package io.github.mjhaugsdal.soap;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.WSS4JConstants;

import java.util.HashMap;
import java.util.Map;

import static org.apache.wss4j.common.ConfigurationConstants.USE_REQ_SIG_CERT;

public class WSTestUtils {

    public static void setupWSSEServer(JaxWsServerFactoryBean bean, boolean encryption, boolean signature) {

        String actions = " ";
        if (encryption) {
            actions = ConfigurationConstants.ENCRYPTION + " ";
        }
        if (signature) {
            actions += ConfigurationConstants.SIGNATURE;
        }

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(ConfigurationConstants.SIG_PROP_FILE, "server/server-sign-in.properties");
        inProps.put(ConfigurationConstants.DEC_PROP_FILE, "server/server-enc-in.properties");
        inProps.put(ConfigurationConstants.ACTION, actions);
        inProps.put(ConfigurationConstants.SIGNATURE_USER, "client"); //alias of signing certificate (public key)
        inProps.put(ConfigurationConstants.ENCRYPTION_USER, "server"); //alias of decryption certificate (private key)
        inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(ConfigurationConstants.SIG_PROP_FILE, "server/server-sign-out.properties");
        outProps.put(ConfigurationConstants.ACTION, actions);
        outProps.put(ConfigurationConstants.SIGNATURE_USER, "server"); //alias of server certificate (private key)
        outProps.put(ConfigurationConstants.ENCRYPTION_USER, USE_REQ_SIG_CERT); //alias of client certificate (public key)
        outProps.put(ConfigurationConstants.ENC_KEY_ID, "DirectReference");
        outProps.put(ConfigurationConstants.SIG_KEY_ID, "SKIKeyIdentifier");
        outProps.put(ConfigurationConstants.INCLUDE_SIGNATURE_TOKEN, "true");
        outProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        outProps.put(ConfigurationConstants.ENC_KEY_TRANSPORT, WSS4JConstants.KEYTRANSPORT_RSAOAEP_XENC11);
        outProps.put(ConfigurationConstants.ENC_SYM_ALGO, WSS4JConstants.AES_256_GCM);
        outProps.put(ConfigurationConstants.SIG_ALGO, WSS4JConstants.RSA_SHA512);
        outProps.put(ConfigurationConstants.SIG_DIGEST_ALGO, WSS4JConstants.SHA512);
        outProps.put(ConfigurationConstants.ENC_MGF_ALGO, WSS4JConstants.MGF_SHA512);
        outProps.put(ConfigurationConstants.ENC_DIGEST_ALGO, WSS4JConstants.SHA512);

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getOutFaultInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);
    }
}
