package io.nettapotek.rest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.ws.rs.Consumes;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import io.nettapotek.rest.hack.types.*;
import no.ergo.reseptformidleren.webservices.na.NAWeb;

@Path("/")
public interface NaWebService extends NAWeb {

    @WebMethod(operationName = "NAWebService_verify", action = "Verify")
    @WebResult(name = "AppRecElement", targetNamespace = "http://na.webservices.reseptformidleren.ergo.no/", partName = "result")
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    AppRec naWebServiceVerify(

        @WebParam(partName = "parameters", name = "MVElement", targetNamespace = "http://na.webservices.reseptformidleren.ergo.no/")
        MV parameters
    );

    @WebMethod(operationName = "NAWebService_m9na1", action = "M9na1")
    @WebResult(name = "M9na2Element", targetNamespace = "http://na.webservices.reseptformidleren.ergo.no/", partName = "result")
    @Path("/m9na1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    M9Na2 naWebServiceM9Na1(

        @WebParam(partName = "parameters", name = "M9na1Element", targetNamespace = "http://na.webservices.reseptformidleren.ergo.no/")
        M9Na1 parameters
    ) throws AppRecFault_Exception;

    @WebMethod(operationName = "NAWebService_m9na3", action = "M9na3")
    @WebResult(name = "M9na4Element", targetNamespace = "http://na.webservices.reseptformidleren.ergo.no/", partName = "result")
    @Path("/m9na3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    M9Na4 naWebServiceM9Na3(

        @WebParam(partName = "parameters", name = "M9na3Element", targetNamespace = "http://na.webservices.reseptformidleren.ergo.no/")
        M9Na3 parameters
    ) throws AppRecFault_Exception;
}
