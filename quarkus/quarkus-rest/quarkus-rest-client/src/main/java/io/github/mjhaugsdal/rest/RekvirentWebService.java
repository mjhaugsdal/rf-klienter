package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.rekvirent.AppRec;
import io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/***
 * This class extends generated code because these endpoints don't offer JAX-RS
 */
@Path("/rekvirent")
@RegisterRestClient
public interface RekvirentWebService {

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M922 rekvirentWebServiceM921(io.github.mjhaugsdal.rest.types.rekvirent.M921 m921Element) throws AppRecFault_Exception;

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M912 rekvirentWebServiceM911(io.github.mjhaugsdal.rest.types.rekvirent.M911 m911Element) throws AppRecFault_Exception;

    @Path("/m5")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    AppRec rekvirentWebServiceM5(io.github.mjhaugsdal.rest.types.rekvirent.M5 m5Element);

    @Path("/m911kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M912Kj rekvirentWebServiceM911Kj(io.github.mjhaugsdal.rest.types.rekvirent.M911Kj m911KjElement) throws AppRecFault_Exception;

    @Path("/m97")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M98 rekvirentWebServiceM97(io.github.mjhaugsdal.rest.types.rekvirent.M97 m97Element) throws AppRecFault_Exception;

    @Path("/m95kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M96Kj rekvirentWebServiceM95Kj(io.github.mjhaugsdal.rest.types.rekvirent.M95Kj m95KjElement) throws AppRecFault_Exception;

    @Path("/m95")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M96 rekvirentWebServiceM95(io.github.mjhaugsdal.rest.types.rekvirent.M95 m95Element);

    @Path("/m251")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    AppRec rekvirentWebServiceM251(io.github.mjhaugsdal.rest.types.rekvirent.M251 m251Element);

    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M272 rekvirentWebServiceM271(io.github.mjhaugsdal.rest.types.rekvirent.M271 m271Element) throws AppRecFault_Exception;


    @Path("/m1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    AppRec rekvirentWebServiceM1(io.github.mjhaugsdal.rest.types.rekvirent.M1 m1Element);

    @Path("/m241")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M242 rekvirentWebServiceM241(io.github.mjhaugsdal.rest.types.rekvirent.M241 m241Element);

    @Path("/m41")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.rekvirent.M42 rekvirentWebServiceM41(io.github.mjhaugsdal.rest.types.rekvirent.M41 m41Element);

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    AppRec rekvirentWebServiceVerify(io.github.mjhaugsdal.rest.types.rekvirent.MV mvElement);
}
