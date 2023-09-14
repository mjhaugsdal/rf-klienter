package io.github.mjhaugsdal.rest.client;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.WSS4JConstants;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class WSS4JOutInterceptorProducer {

    boolean encryption = true;

    boolean signature = true;

    private final Map<String, String> passwords = new HashMap<>();

    public WSS4JOutInterceptorProducer() {
        passwords.put("client", "password");
        passwords.put("server", "password");
    }


    @Produces
    @Unremovable
    @ApplicationScoped
    @Named("clientOutInterceptor")
    WSS4JOutInterceptor wssInterceptor() {
        String actions = " ";
        if (encryption) {
            actions = ConfigurationConstants.ENCRYPTION + " ";
        }
        if (signature) {
            actions += ConfigurationConstants.SIGNATURE;
        }


        Map<String, Object> outProps = new HashMap<>();
        outProps.put(ConfigurationConstants.SIG_PROP_FILE, "client/client-sign-out.properties");
        outProps.put(ConfigurationConstants.ENC_PROP_FILE, "client/client-enc-out.properties");
        outProps.put(ConfigurationConstants.ACTION, actions);
        outProps.put(ConfigurationConstants.SIGNATURE_USER, "client"); //alias of client certificate (private key)
        outProps.put(ConfigurationConstants.ENCRYPTION_USER, "server"); //alias of server certificate (public key)
        outProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());
        outProps.put(ConfigurationConstants.ENC_KEY_ID, "DirectReference");
        outProps.put(ConfigurationConstants.SIG_KEY_ID, "SKIKeyIdentifier");
        outProps.put(ConfigurationConstants.INCLUDE_SIGNATURE_TOKEN, "true");

        outProps.put(ConfigurationConstants.ENC_KEY_TRANSPORT, WSS4JConstants.KEYTRANSPORT_RSAOAEP_XENC11);
        outProps.put(ConfigurationConstants.ENC_SYM_ALGO, WSS4JConstants.AES_256_GCM);
        outProps.put(ConfigurationConstants.SIG_ALGO, WSS4JConstants.RSA_SHA512);
        outProps.put(ConfigurationConstants.SIG_DIGEST_ALGO, WSS4JConstants.SHA512);
        outProps.put(ConfigurationConstants.ENC_MGF_ALGO, WSS4JConstants.MGF_SHA512);
        outProps.put(ConfigurationConstants.ENC_DIGEST_ALGO, WSS4JConstants.SHA512);

        return new WSS4JOutInterceptor(outProps);
    }

}
