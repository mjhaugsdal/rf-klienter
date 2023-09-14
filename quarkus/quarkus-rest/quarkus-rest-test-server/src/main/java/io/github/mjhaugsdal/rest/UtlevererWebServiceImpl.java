package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.utleverer.AppRec;
import io.github.mjhaugsdal.rest.types.utleverer.M272;
import io.github.mjhaugsdal.rest.types.utleverer.M912;
import io.github.mjhaugsdal.rest.types.utleverer.M92;
import io.github.mjhaugsdal.rest.types.utleverer.M922;
import io.github.mjhaugsdal.rest.types.utleverer.M94;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


public class UtlevererWebServiceImpl implements UtlevererWebService {

    @Path("/m10")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM10(io.github.mjhaugsdal.rest.types.utleverer.M10 m10Element) {
        var response = new AppRec();
        response.setDokument(m10Element.getDokument());
        return response;
    }

    @Path("/m253")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM253(io.github.mjhaugsdal.rest.types.utleverer.M253 m253Element) {
        var response = new AppRec();
        response.setDokument(m253Element.getDokument());
        return response;
    }

    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M272 utlevererWebServiceM271(io.github.mjhaugsdal.rest.types.utleverer.M271 m271Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        var response = new M272();
        response.setDokument(m271Element.getDokument());
        return response;
    }

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceVerify(io.github.mjhaugsdal.rest.types.utleverer.MV mvElement) {
        var response = new AppRec();
        response.setDokument(mvElement.getDokument());
        return response;
    }

    @Path("/m252")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM252(io.github.mjhaugsdal.rest.types.utleverer.M252 m252Element) {
        var response = new AppRec();
        response.setDokument(m252Element.getDokument());
        return response;
    }

    @Path("/m93")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M94 utlevererWebServiceM93(io.github.mjhaugsdal.rest.types.utleverer.M93 m93Element) {
        var response = new M94();
        response.setDokument(m93Element.getDokument());
        return response;
    }

    @Path("/m91")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M92 utlevererWebServiceM91(io.github.mjhaugsdal.rest.types.utleverer.M91 m91Element) {
        var response = new M92();
        response.setDokument(m91Element.getDokument());
        return response;
    }

    @Path("/m3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST

    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM3(io.github.mjhaugsdal.rest.types.utleverer.M3 m3Element) {
        var response = new AppRec();
        response.setDokument(m3Element.getDokument());
        return response;
    }

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M912 utlevererWebServiceM911(io.github.mjhaugsdal.rest.types.utleverer.M911 m911Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        var response = new M912();
        response.setDokument(m911Element.getDokument());
        return response;
    }

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.utleverer.M922 utlevererWebServiceM921(io.github.mjhaugsdal.rest.types.utleverer.M921 m921Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        var response = new M922();
        response.setDokument(m921Element.getDokument());
        return response;
    }
}
