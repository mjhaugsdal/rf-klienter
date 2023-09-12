package io.github.mjhaugsdal.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

public class RekvirentWebServiceImpl implements RekvirentWebService{

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M922 rekvirentWebServiceM921(io.github.mjhaugsdal.rest.types.rekvirent.M921 m921Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M922();
    }

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M912 rekvirentWebServiceM911(io.github.mjhaugsdal.rest.types.rekvirent.M911 m911Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M912();
    }

    @Path("/m5")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM5(io.github.mjhaugsdal.rest.types.rekvirent.M5 m5Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }

    @Path("/m911kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M912Kj rekvirentWebServiceM911Kj(io.github.mjhaugsdal.rest.types.rekvirent.M911Kj m911KjElement) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M912Kj();
    }

    @Path("/m97")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M98 rekvirentWebServiceM97(io.github.mjhaugsdal.rest.types.rekvirent.M97 m97Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M98();
    }

    @Path("/m95kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M96Kj rekvirentWebServiceM95Kj(io.github.mjhaugsdal.rest.types.rekvirent.M95Kj m95KjElement) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M96Kj();
    }

    @Path("/m95")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M96 rekvirentWebServiceM95(io.github.mjhaugsdal.rest.types.rekvirent.M95 m95Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M96();
    }

    @Path("/m251")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM251(io.github.mjhaugsdal.rest.types.rekvirent.M251 m251Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }

    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M272 rekvirentWebServiceM271(io.github.mjhaugsdal.rest.types.rekvirent.M271 m271Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M272();
    }

    @Path("/m1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM1(io.github.mjhaugsdal.rest.types.rekvirent.M1 m1Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }

    @Path("/m241")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M242 rekvirentWebServiceM241(io.github.mjhaugsdal.rest.types.rekvirent.M241 m241Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M242();
    }

    @Path("/m41")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M42 rekvirentWebServiceM41(io.github.mjhaugsdal.rest.types.rekvirent.M41 m41Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M42();
    }

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceVerify(io.github.mjhaugsdal.rest.types.rekvirent.MV mvElement) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }
}
