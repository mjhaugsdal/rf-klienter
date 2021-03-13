package im.haugsdal.playground;


import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import im.haugsdal.playground.nettapotek.gateway.NettapotekService;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.openapi.OpenApiFeature;
import org.apache.cxf.jaxrs.provider.MultipartProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import static java.security.Security.addProvider;

public class Application {

    public static void main(String[] args) throws Exception {
        new Application();
        System.out.println("Server ready...");

        Thread.sleep(5 * 6000 * 1000);
        System.out.println("Server exiting");
        System.exit(0);

    }

    protected Application() throws Exception {
        addProvider(new BouncyCastleProvider());

        Server server = new Server();

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(8888);
        httpConfig.setOutputBufferSize(32768);


        SslContextFactory sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath("src/main/resources/keystore.p12");
        sslContextFactory.setKeyStorePassword("changeit");
        sslContextFactory.setKeyStoreType("PKCS12");

        HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
        SecureRequestCustomizer src = new SecureRequestCustomizer();
        src.setStsMaxAge(2000);
        src.setStsIncludeSubDomains(true);
        httpsConfig.addCustomizer(src);

        ServerConnector https = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig)
        );
        https.setPort(8888);
        https.setIdleTimeout(500000);

        final OpenApiFeature feature = new OpenApiFeature();
        feature.setContactEmail("cxf@apache.org");
        feature.setLicense("Apache 2.0 License");
        feature.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        //feature.setSecurityDefinitions(Collections.singletonMap("basicAuth",new SecurityScheme().type(Type.HTTP)));

        final ServletHolder servletHolder = new ServletHolder(new CXFNonSpringJaxrsServlet());
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(servletHolder, "/*");
        servletHolder.setInitParameter("jaxrs.serviceClasses", NettapotekService.class.getName());
        servletHolder.setInitParameter("jaxrs.features",
                OpenApiFeature.class.getName());
        servletHolder.setInitParameter("jaxrs.providers", StringUtils.join(
                new String[] {
                        MultipartProvider.class.getName(),
                        JacksonXMLProvider.class.getName()
                        // ApiOriginFilter.class.getName()
                }, ",")
        );

        server.setConnectors(new Connector[]{https});
        server.setHandler(context);
        server.start();
        server.join();
    }
}
