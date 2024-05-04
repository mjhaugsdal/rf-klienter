package io.github.mjhaugsdal.fhir;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.JAXBElement;
import org.hl7.fhir.Patient;

@Path("/fhir/patient")
public interface PatientService {


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    JAXBElement<Patient> getPatient(@QueryParam("id") int id);

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    JAXBElement<Patient> postPatient(JAXBElement<Patient> patient);
}
