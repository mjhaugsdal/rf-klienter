package io.nettapotek;

import no.ergo.reseptformidleren.webservices.na.*;

public class NaWebService implements NAWeb {
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
