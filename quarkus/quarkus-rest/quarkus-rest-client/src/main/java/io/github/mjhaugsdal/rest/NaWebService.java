package io.github.mjhaugsdal.rest;


import io.github.mjhaugsdal.rest.types.na.AppRec;
import io.github.mjhaugsdal.rest.types.na.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.na.M9Na1;
import io.github.mjhaugsdal.rest.types.na.M9Na2;
import io.github.mjhaugsdal.rest.types.na.M9Na3;
import io.github.mjhaugsdal.rest.types.na.M9Na4;
import io.github.mjhaugsdal.rest.types.na.MV;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/***
 * This class extends generated code because these endpoints don't offer JAX-RS
 */
@Path("/na")
@RegisterRestClient
public interface NaWebService {

    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    AppRec naWebServiceVerify(
            MV parameters
    );

    @Path("/m9na1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    M9Na2 naWebServiceM9Na1(
            M9Na1 parameters
    ) throws AppRecFault_Exception;

    @Path("/m9na3")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    M9Na4 naWebServiceM9Na3(
            M9Na3 parameters
    ) throws AppRecFault_Exception;
}
