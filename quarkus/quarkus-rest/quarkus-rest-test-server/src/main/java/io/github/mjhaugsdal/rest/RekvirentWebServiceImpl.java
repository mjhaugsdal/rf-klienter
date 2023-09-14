package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.rekvirent.AppRec;
import io.github.mjhaugsdal.rest.types.rekvirent.M242;
import io.github.mjhaugsdal.rest.types.rekvirent.M272;
import io.github.mjhaugsdal.rest.types.rekvirent.M42;
import io.github.mjhaugsdal.rest.types.rekvirent.M912;
import io.github.mjhaugsdal.rest.types.rekvirent.M912Kj;
import io.github.mjhaugsdal.rest.types.rekvirent.M922;
import io.github.mjhaugsdal.rest.types.rekvirent.M96;
import io.github.mjhaugsdal.rest.types.rekvirent.M96Kj;
import io.github.mjhaugsdal.rest.types.rekvirent.M98;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class RekvirentWebServiceImpl implements RekvirentWebService {

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M922 rekvirentWebServiceM921(io.github.mjhaugsdal.rest.types.rekvirent.M921 m921Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        var response = new M922();
        response.setDokument(m921Element.getDokument());
        return response;
    }

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M912 rekvirentWebServiceM911(io.github.mjhaugsdal.rest.types.rekvirent.M911 m911Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        var response = new M912();
        response.setDokument(m911Element.getDokument());
        return response;
    }

    @Path("/m5")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM5(io.github.mjhaugsdal.rest.types.rekvirent.M5 m5Element) {
        var response = new AppRec();
        response.setDokument(m5Element.getDokument());
        return response;
    }

    @Path("/m911kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M912Kj rekvirentWebServiceM911Kj(io.github.mjhaugsdal.rest.types.rekvirent.M911Kj m911KjElement) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        var response = new M912Kj();
        response.setDokument(m911KjElement.getDokument());
        return response;
    }

    @Path("/m97")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M98 rekvirentWebServiceM97(io.github.mjhaugsdal.rest.types.rekvirent.M97 m97Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        var response = new M98();
        response.setDokument(m97Element.getDokument());
        return response;
    }

    @Path("/m95kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M96Kj rekvirentWebServiceM95Kj(io.github.mjhaugsdal.rest.types.rekvirent.M95Kj m95KjElement) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        var response = new M96Kj();
        response.setDokument(m95KjElement.getDokument());
        return response;
    }

    @Path("/m95")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M96 rekvirentWebServiceM95(io.github.mjhaugsdal.rest.types.rekvirent.M95 m95Element) {
        var response = new M96();
        response.setDokument(m95Element.getDokument());
        return response;
    }

    @Path("/m251")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM251(io.github.mjhaugsdal.rest.types.rekvirent.M251 m251Element) {
        var response = new AppRec();
        response.setDokument(m251Element.getDokument());
        return response;
    }

    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M272 rekvirentWebServiceM271(io.github.mjhaugsdal.rest.types.rekvirent.M271 m271Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        var response = new M272();
        response.setDokument(m271Element.getDokument());
        return response;
    }

    @Path("/m1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM1(io.github.mjhaugsdal.rest.types.rekvirent.M1 m1Element) {
        var response = new AppRec();
        response.setDokument(m1Element.getDokument());
        return response;
    }

    @Path("/m241")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M242 rekvirentWebServiceM241(io.github.mjhaugsdal.rest.types.rekvirent.M241 m241Element) {
        var response = new M242();
        response.setDokument(m241Element.getDokument());
        return response;
    }

    @Path("/m41")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M42 rekvirentWebServiceM41(io.github.mjhaugsdal.rest.types.rekvirent.M41 m41Element) {
        var response = new M42();
        response.setDokument(m41Element.getDokument());
        return response;
    }

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceVerify(io.github.mjhaugsdal.rest.types.rekvirent.MV mvElement) {
        var response = new AppRec();
        response.setDokument(mvElement.getDokument());
        return response;
    }
}
