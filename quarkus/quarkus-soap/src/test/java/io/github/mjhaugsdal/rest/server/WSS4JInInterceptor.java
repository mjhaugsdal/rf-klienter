package io.github.mjhaugsdal.rest.server;

import java.util.Map;

public class WSS4JInInterceptor extends org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor {
    public WSS4JInInterceptor(Map<String, Object> inProps) {
        super(inProps);
    }
}
