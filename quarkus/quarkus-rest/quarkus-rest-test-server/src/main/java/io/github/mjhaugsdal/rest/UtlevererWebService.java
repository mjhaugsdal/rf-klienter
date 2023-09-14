package io.github.mjhaugsdal.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/***
 * This class extends generated code because these endpoints don't offer JAX-RS
 */
@Path("/utleverer")
//@RegisterRestClient
public interface UtlevererWebService {

    @Path("/m10")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM10(

            io.github.mjhaugsdal.rest.types.utleverer.M10 m10Element
    );

    @Path("/m253")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM253(

            io.github.mjhaugsdal.rest.types.utleverer.M253 m253Element
    );


    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M272 utlevererWebServiceM271(

            io.github.mjhaugsdal.rest.types.utleverer.M271 m271Element
    ) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceVerify(

            io.github.mjhaugsdal.rest.types.utleverer.MV mvElement
    );

    @Path("/m252")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM252(

            io.github.mjhaugsdal.rest.types.utleverer.M252 m252Element
    );

    @Path("/m93")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M94 utlevererWebServiceM93(

            io.github.mjhaugsdal.rest.types.utleverer.M93 m93Element
    );

    @Path("/m91")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M92 utlevererWebServiceM91(

            io.github.mjhaugsdal.rest.types.utleverer.M91 m91Element
    );

    @Path("/m3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM3(

            io.github.mjhaugsdal.rest.types.utleverer.M3 m3Element
    );

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M912 utlevererWebServiceM911(

            io.github.mjhaugsdal.rest.types.utleverer.M911 m911Element
    ) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M922 utlevererWebServiceM921(

            io.github.mjhaugsdal.rest.types.utleverer.M921 m921Element
    ) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;
}
