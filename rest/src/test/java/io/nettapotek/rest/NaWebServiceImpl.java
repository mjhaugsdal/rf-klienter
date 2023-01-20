package io.nettapotek.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


import io.nettapotek.rest.hack.types.*;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.rs.security.jose.jaxrs.*;


public class NaWebServiceImpl implements NaWebService {

    public static void main(String[]args) {
        new NaWebServiceImpl(true, "");
    }
    /*static {

    }*/

    public NaWebServiceImpl() {
        //
    }

    public NaWebServiceImpl(boolean jwk, String context) {
        var serverFactoryBean = new JAXRSServerFactoryBean();
        serverFactoryBean.setServiceClass(NaWebServiceImpl.class);
        serverFactoryBean.setAddress("http://localhost:8890/" + context);
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        serverFactoryBean.getFeatures().add(loggingFeature);

        List<Object> providers = new LinkedList<>();
        providers.add("com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider");

        var jweContainerRequestFilter = new JweContainerRequestFilter();
        var jwsContainerRequestFilter = new JwsContainerRequestFilter();
        //var jwsContainerRequestFilter = new JwsJsonContainerRequestFilter();

        Properties properties = new Properties();
        try {
            properties.load(NaWebServiceImpl.class.getClassLoader().getResourceAsStream("server/server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CustomJwsContainerRequestFilter jwsContainerRequestFilter = new CustomJwsContainerRequestFilter();
        //CustomJweWriterInterceptor jweWriterInterceptor = new CustomJweWriterInterceptor(properties); //This combined with CustomJwsWriterInterceptor implements encryption using request signing certificate
        var jweWriterInterceptor = new JweWriterInterceptor();
        jweWriterInterceptor.setUseJweOutputStream(true);

        var jwsWriterInterceptor = new JwsWriterInterceptor();
        jwsWriterInterceptor.setUseJwsOutputStream(true);

        providers.add(jweContainerRequestFilter);
        providers.add(jwsContainerRequestFilter);
        providers.add(jweWriterInterceptor);
        providers.add(jwsWriterInterceptor);

        serverFactoryBean.setProviders(providers);

        if(jwk) {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/jwk/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/jwk/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/jwk/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/jwk/server-out-sign.properties"
            );

            serverFactoryBean.getProperties(true).put("rs.security.accept.public.key", "true");
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.public.key", "true");
        } else {
            //ENCRYPTION properties
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.in.properties",
                    "server/server.properties"
            );
            serverFactoryBean.getProperties(true).put(
                    "rs.security.encryption.out.properties",
                    "server/server-out.properties"
            );
            //SIGNATURE IN
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.in.properties",
                    "server/server-sign.properties"
            );
            //SIGNATURE OUT
            serverFactoryBean.getProperties(true).put(
                    "rs.security.signature.out.properties",
                    "server/server-out-sign.properties"
            );
            serverFactoryBean.getProperties(true).put("rs.security.signature.include.cert", "true");

        }

        serverFactoryBean.getProperties(true).put("jose.debug", true);
        serverFactoryBean.create();
    }

    @Override
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    public AppRec naWebServiceVerify(MV parameters) {
        return new AppRec();
    }

    @Override
    @Path("/m9na1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {
        var m9na2 = new M9Na2();
        m9na2.setDokument("Hello World!".getBytes(StandardCharsets.UTF_8));
        return m9na2;
    }

    @Override
    @Path("m9na3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        return new M9Na4();
    }

    @Override
    public no.ergo.reseptformidleren.webservices.na.AppRec naWebServiceVerify(no.ergo.reseptformidleren.webservices.na.MV parameters) {
        return null;
    }

    @Override
    public no.ergo.reseptformidleren.webservices.na.M9Na2 naWebServiceM9Na1(no.ergo.reseptformidleren.webservices.na.M9Na1 parameters) throws no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception {
        return null;
    }

    @Override
    public no.ergo.reseptformidleren.webservices.na.M9Na4 naWebServiceM9Na3(no.ergo.reseptformidleren.webservices.na.M9Na3 parameters) throws no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception {
        return null;
    }
}
