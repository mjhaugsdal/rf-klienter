package io.github.mjhaugsdal.rest.util;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.rs.security.jose.jaxrs.JweClientResponseFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JweWriterInterceptor;
import org.apache.cxf.rs.security.jose.jaxrs.JwsClientResponseFilter;
import org.apache.cxf.rs.security.jose.jaxrs.JwsWriterInterceptor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class RSUtils {

    public static void setupRSJOSEClient(JAXRSClientFactoryBean bean, boolean encryption, boolean signature, List<Object> providers) {


    }
}
