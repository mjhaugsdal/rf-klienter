package io.github.mjhaugsdal.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


public class UtlevererWebServiceImpl implements UtlevererWebService{

    @Path("/m10")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM10(io.github.mjhaugsdal.rest.types.utleverer.M10 m10Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Path("/m253")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM253(io.github.mjhaugsdal.rest.types.utleverer.M253 m253Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M272 utlevererWebServiceM271(io.github.mjhaugsdal.rest.types.utleverer.M271 m271Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.utleverer.M272();
    }

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceVerify(io.github.mjhaugsdal.rest.types.utleverer.MV mvElement) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Path("/m252")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM252(io.github.mjhaugsdal.rest.types.utleverer.M252 m252Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Path("/m93")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M94 utlevererWebServiceM93(io.github.mjhaugsdal.rest.types.utleverer.M93 m93Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.M94();
    }

    @Path("/m91")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M92 utlevererWebServiceM91(io.github.mjhaugsdal.rest.types.utleverer.M91 m91Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.M92();
    }

    @Path("/m3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST

    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM3(io.github.mjhaugsdal.rest.types.utleverer.M3 m3Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M912 utlevererWebServiceM911(io.github.mjhaugsdal.rest.types.utleverer.M911 m911Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.utleverer.M912();
    }

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M922 utlevererWebServiceM921(io.github.mjhaugsdal.rest.types.utleverer.M921 m921Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.utleverer.M922();
    }
}
