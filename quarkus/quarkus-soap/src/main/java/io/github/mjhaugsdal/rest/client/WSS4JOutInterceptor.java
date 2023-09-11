package io.github.mjhaugsdal.rest.client;

import java.util.Map;

public class WSS4JOutInterceptor extends org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor {
    public WSS4JOutInterceptor(Map<String, Object> outProps) {
        super(outProps);
    }
}
