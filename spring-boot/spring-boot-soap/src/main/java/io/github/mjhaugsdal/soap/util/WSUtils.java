package io.github.mjhaugsdal.soap.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.WSS4JConstants;
import org.apache.wss4j.common.ext.WSSecurityException;

import java.util.HashMap;
import java.util.Map;

public class WSUtils {

    public static void setupWSSEClient(JaxWsProxyFactoryBean bean, boolean encryption, boolean signature, boolean ecdh) throws WSSecurityException {
        String actions = " ";
        if (encryption) {
            actions = ConfigurationConstants.ENCRYPTION + " ";
        }
        if (signature) {
            actions += ConfigurationConstants.SIGNATURE;
        }
        String encryptionMethod = "";
        if (ecdh) {
            encryptionMethod = "ecdh";
        }

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(ConfigurationConstants.SIG_PROP_FILE, "client/" + encryptionMethod + "/client-sign-in.properties");
        inProps.put(ConfigurationConstants.DEC_PROP_FILE, "client/" + encryptionMethod + "/client-enc-in.properties");
        inProps.put(ConfigurationConstants.ACTION, actions);
        inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        var wssIn = new WSS4JInInterceptor(inProps);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(ConfigurationConstants.SIG_PROP_FILE, "client/" + encryptionMethod + "/client-sign-out.properties");
        outProps.put(ConfigurationConstants.ENC_PROP_FILE, "client/" + encryptionMethod + "/client-enc-out.properties");
        outProps.put(ConfigurationConstants.ACTION, actions);
        outProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());
        outProps.put(ConfigurationConstants.ENC_KEY_ID, "DirectReference");
        outProps.put(ConfigurationConstants.SIG_KEY_ID, "SKIKeyIdentifier");
        outProps.put(ConfigurationConstants.INCLUDE_SIGNATURE_TOKEN, "true");

        if (!ecdh) {
            inProps.put(ConfigurationConstants.SIGNATURE_USER, "server"); //alias of signing certificate (public key)
            inProps.put(ConfigurationConstants.ENCRYPTION_USER, "client"); //alias of decryption certificate (private key)
            outProps.put(ConfigurationConstants.SIGNATURE_USER, "client"); //alias of server certificate (private key)
            outProps.put(ConfigurationConstants.ENCRYPTION_USER, "server"); //alias of server certificate (public key)

            outProps.put(ConfigurationConstants.ENC_KEY_TRANSPORT, WSS4JConstants.KEYTRANSPORT_RSAOAEP_XENC11);
            outProps.put(ConfigurationConstants.ENC_SYM_ALGO, WSS4JConstants.AES_256_GCM);
            outProps.put(ConfigurationConstants.ENC_MGF_ALGO, WSS4JConstants.MGF_SHA512);
            outProps.put(ConfigurationConstants.ENC_DIGEST_ALGO, WSS4JConstants.SHA512);
            outProps.put(ConfigurationConstants.SIG_ALGO, WSS4JConstants.RSA_SHA512);
            outProps.put(ConfigurationConstants.SIG_DIGEST_ALGO, WSS4JConstants.SHA512);

        } else {
            inProps.put(ConfigurationConstants.SIGNATURE_USER, "server-ecdh"); //alias of signing certificate (public key)
            inProps.put(ConfigurationConstants.ENCRYPTION_USER, "client-ecdh"); //alias of decryption certificate (private key)
            outProps.put(ConfigurationConstants.SIGNATURE_USER, "client-ecdh"); //alias of server certificate (private key)
            outProps.put(ConfigurationConstants.ENCRYPTION_USER, "server-ecdh"); //alias of server certificate (public key)

            outProps.put(ConfigurationConstants.ENC_SYM_ALGO, WSS4JConstants.AES_256_GCM);
            outProps.put(ConfigurationConstants.ENC_KEY_TRANSPORT, WSS4JConstants.KEYWRAP_AES256);
            outProps.put(ConfigurationConstants.ENC_KEY_AGREEMENT_METHOD, WSS4JConstants.AGREEMENT_METHOD_ECDH_ES);
            outProps.put(ConfigurationConstants.SIG_ALGO, WSS4JConstants.ECDSA_SHA512);
        }

        var wssOut = new WSS4JOutInterceptor(outProps);
        bean.getOutInterceptors().add(wssOut);
        bean.getInInterceptors().add(wssIn);
    }
}
