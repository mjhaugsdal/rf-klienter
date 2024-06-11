package io.github.mjhaugsdal.soap.util;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;

public class PasswordCallback implements CallbackHandler {

    private final Map<String, String> passwords = new HashMap<>();

    public PasswordCallback() {
        passwords.put("client", "password");
        passwords.put("server", "password");
        passwords.put("client-ecdh", "");
        passwords.put("server-ecdh", "");
    }

    @Override
    public void handle(Callback[] callbacks) {
        for (Callback callback : callbacks) {
            WSPasswordCallback pc = (WSPasswordCallback) callback;
            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
    }
}