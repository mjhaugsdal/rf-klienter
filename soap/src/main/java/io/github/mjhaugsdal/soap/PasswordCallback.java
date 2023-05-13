package io.github.mjhaugsdal.soap;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordCallback implements CallbackHandler {
    private final Map<String, String> passwords = new HashMap<>();

    public PasswordCallback() {
        passwords.put("client", "password");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException {
        for (Callback callback : callbacks) {
            WSPasswordCallback pc = (WSPasswordCallback) callback;
            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
        throw new IOException();
    }
}