package io.github.mjhaugsdal.rest;

import jakarta.jws.WebParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;


@Path("/")
public interface UtlevererWebService extends UtlevererWeb {

    @Path("/m10")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM10(

            @WebParam(partName = "M10Element", name = "M10Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M10 m10Element
    );

    @Path("/m253")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM253(

            @WebParam(partName = "M25_3Element", name = "M25_3Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M253 m253Element
    );


    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M272 utlevererWebServiceM271(

            @WebParam(partName = "M27_1Element", name = "M27_1Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M271 m271Element
    ) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceVerify(

            @WebParam(partName = "MVElement", name = "MVElement", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.MV mvElement
    );

    @Path("/m252")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM252(

            @WebParam(partName = "M25_2Element", name = "M25_2Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M252 m252Element
    );

    @Path("/m93")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M94 utlevererWebServiceM93(

            @WebParam(partName = "M9_3Element", name = "M9_3Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M93 m93Element
    );

    @Path("/m91")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M92 utlevererWebServiceM91(

            @WebParam(partName = "M9_1Element", name = "M9_1Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M91 m91Element
    );

    @Path("/m3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM3(

            @WebParam(partName = "M3Element", name = "M3Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M3 m3Element
    );

    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M912 utlevererWebServiceM911(

            @WebParam(partName = "M9_11Element", name = "M9_11Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M911 m911Element
    ) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;

    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    io.github.mjhaugsdal.rest.types.utleverer.M922 utlevererWebServiceM921(

            @WebParam(partName = "M9_21Element", name = "M9_21Element", targetNamespace = "http://utleverer.webservices.reseptformidleren.ergo.no/")
            io.github.mjhaugsdal.rest.types.utleverer.M921 m921Element
    ) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;
}
