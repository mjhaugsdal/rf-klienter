package io.github.mjhaugsdal.rest.impl;

import jakarta.jws.WebService;
import no.ergo.reseptformidleren.webservices.na.AppRec;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na2;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.M9Na4;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;

@WebService(serviceName = "NaWebService", portName = "NaWebService", name = "NaWebService", endpointInterface = "no.ergo.reseptformidleren.webservices.na.NAWeb")
public class NaWebServiceImpl implements NAWeb {
    @Override
    public AppRec naWebServiceVerify(MV parameters) {
        return new AppRec();
    }

    @Override
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {
        return new M9Na2();
    }

    @Override
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        return new M9Na4();
    }
}
