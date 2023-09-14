package io.github.mjhaugsdal.rest.client;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.apache.wss4j.common.ConfigurationConstants;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class WSS4JInInterceptorProducer {

    boolean encryption = true;

    boolean signature = true;

    private final Map<String, String> passwords = new HashMap<>();

    public WSS4JInInterceptorProducer() {
        passwords.put("client", "password");
        passwords.put("server", "password");
    }


    @Produces
    @Unremovable
    @ApplicationScoped
    WSS4JInInterceptor wssInterceptor() {
        String actions = " ";
        if (encryption) {
            actions = ConfigurationConstants.ENCRYPTION + " ";
        }
        if (signature) {
            actions += ConfigurationConstants.SIGNATURE;
        }

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(ConfigurationConstants.SIG_PROP_FILE, "client/client-sign-in.properties");
        inProps.put(ConfigurationConstants.DEC_PROP_FILE, "client/client-enc-in.properties");
        inProps.put(ConfigurationConstants.ACTION, actions);
        inProps.put(ConfigurationConstants.SIGNATURE_USER, "server"); //alias of signing certificate (public key)
        inProps.put(ConfigurationConstants.ENCRYPTION_USER, "client"); //alias of decryption certificate (private key)
        inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());

        return new WSS4JInInterceptor(inProps);
    }
}
