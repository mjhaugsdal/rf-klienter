package io.github.mjhaugsdal.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import no.ergo.reseptformidleren.webservices.rekvirent.*;

@Path("/")
public class RekvirentWebServiceImpl implements RekvirentWebService {


    @Override
    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M922 rekvirentWebServiceM921(M921 m921Element) throws AppRecFault_Exception {
        return null;
    }

    @Override
    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M912 rekvirentWebServiceM911(M911 m911Element) throws AppRecFault_Exception {
        return null;
    }

    @Override
    @Path("/m5")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public AppRec rekvirentWebServiceM5(M5 m5Element) {
        return null;
    }

    @Override
    @Path("/m911kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M912Kj rekvirentWebServiceM911Kj(M911Kj m911KjElement) throws AppRecFault_Exception {
        return null;
    }

    @Override
    @Path("/m97")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M98 rekvirentWebServiceM97(M97 m97Element) throws AppRecFault_Exception {
        return null;
    }

    @Override
    @Path("/m95kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M96Kj rekvirentWebServiceM95Kj(M95Kj m95KjElement) throws AppRecFault_Exception {
        return null;
    }

    @Override
    @Path("/m95")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M96 rekvirentWebServiceM95(M95 m95Element) {
        return null;
    }

    @Override
    @Path("/m251")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public AppRec rekvirentWebServiceM251(M251 m251Element) {
        return null;
    }

    @Override
    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M272 rekvirentWebServiceM271(M271 m271Element) throws AppRecFault_Exception {
        return null;
    }

    @Override
    public AppRec rekvirentWebServiceM1(M1 m1Element) {
        return null;
    }

    @Override
    @Path("/m1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM1(io.github.mjhaugsdal.rest.types.rekvirent.M1 m1Element) {
        return null;
    }

    @Override
    @Path("/m241")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M242 rekvirentWebServiceM241(M241 m241Element) {
        return null;
    }

    @Override
    @Path("/m41")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M42 rekvirentWebServiceM41(M41 m41Element) {
        return null;
    }

    @Override
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public AppRec rekvirentWebServiceVerify(MV mvElement) {
        return null;
    }
}
