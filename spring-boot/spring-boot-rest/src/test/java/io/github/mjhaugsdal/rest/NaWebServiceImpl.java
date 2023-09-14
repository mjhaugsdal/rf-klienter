package io.github.mjhaugsdal.rest;


import io.github.mjhaugsdal.rest.types.na.AppRec;
import io.github.mjhaugsdal.rest.types.na.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.na.M9Na1;
import io.github.mjhaugsdal.rest.types.na.M9Na2;
import io.github.mjhaugsdal.rest.types.na.M9Na3;
import io.github.mjhaugsdal.rest.types.na.M9Na4;
import io.github.mjhaugsdal.rest.types.na.MV;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


public class NaWebServiceImpl implements NaWebService {


    public NaWebServiceImpl() {
        //
    }

    @Override
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public AppRec naWebServiceVerify(MV parameters) {
        return new AppRec();
    }

    @Override
    @Path("/m9na1")
    @Consumes(MediaType.APPLICATION_XML + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {
        return new M9Na2();
    }

    @Override
    @Path("m9na3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        return new M9Na4();
    }

    @Override
    @Deprecated
    public no.ergo.reseptformidleren.webservices.na.AppRec naWebServiceVerify(no.ergo.reseptformidleren.webservices.na.MV parameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public no.ergo.reseptformidleren.webservices.na.M9Na2 naWebServiceM9Na1(no.ergo.reseptformidleren.webservices.na.M9Na1 parameters) throws no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public no.ergo.reseptformidleren.webservices.na.M9Na4 naWebServiceM9Na3(no.ergo.reseptformidleren.webservices.na.M9Na3 parameters) throws no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
}
