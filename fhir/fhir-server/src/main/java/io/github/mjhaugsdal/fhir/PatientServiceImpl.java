package io.github.mjhaugsdal.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import jakarta.ws.rs.core.Response;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Patient;

import java.util.concurrent.ConcurrentHashMap;

public class PatientServiceImpl implements PatientService {

    private static Long counter = 1L;

    private static final FhirContext fhirContext = FhirContext.forDstu3();
    private static final ConcurrentHashMap<String, Patient> patients = new ConcurrentHashMap<>();

    private static final IParser parser;

    static {
        patients.put(String.valueOf(counter), createPatient("Van Houte"));
        patients.put(String.valueOf(counter), createPatient("Agnes"));
        for (int i = 0; i < 20; i++) {
            patients.put(String.valueOf(counter), createPatient("Random Patient " + counter));
        }
        parser = fhirContext.newJsonParser();
        parser.setPrettyPrint(true);
    }

    @Override
    public Response getPatient(int id) {
        var patient = patients.get(String.valueOf(id));
        return Response.accepted().entity(parser.encodeResourceToString(patient)).build();
    }

    @Override
    public Response createPatient(Patient patient) {
        patient.setId(createId(counter));
        patients.put(String.valueOf(counter), patient);
        return Response.ok().entity(parser.encodeResourceToString(patient)).build();
    }

    private static IdType createId(final Long id) {
        return new IdType("Patient", "" + id, "" + 1L);
    }

    private static Patient createPatient(final String name) {
        final Patient patient = new Patient();
        patient.getName().add(new HumanName().setFamily(name));
        patient.setId(createId(counter));
        counter++;
        return patient;
    }
}
