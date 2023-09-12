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
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


public class NaWebServiceImpl implements NaWebService {

    public AppRec naWebServiceVerify(MV parameters) {
        return new AppRec();
    }

    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {
        return new M9Na2();
    }

    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        return new M9Na4();
    }
}
