package io.nettapotek.rest;



import io.nettapotek.rest.hack.types.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
