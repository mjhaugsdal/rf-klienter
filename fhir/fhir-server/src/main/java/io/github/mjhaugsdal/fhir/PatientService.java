package io.github.mjhaugsdal.fhir;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hl7.fhir.dstu3.model.Patient;

@Path("/fhir/patient")
public interface PatientService {


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getPatient(@QueryParam("id") int id);

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response createPatient(Patient patient);
}
