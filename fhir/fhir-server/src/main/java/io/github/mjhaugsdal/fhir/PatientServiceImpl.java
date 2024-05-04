package io.github.mjhaugsdal.fhir;


import jakarta.xml.bind.JAXBElement;
import org.hl7.fhir.HumanName;
import org.hl7.fhir.Id;
import org.hl7.fhir.ObjectFactory;
import org.hl7.fhir.Patient;
import org.hl7.fhir.String;

import java.util.concurrent.ConcurrentHashMap;

public class PatientServiceImpl implements PatientService {

    private static Long counter = 1L;

    //    private static final FhirContext fhirContext = FhirContext.forDstu3();
    private static final ConcurrentHashMap<java.lang.String, Patient> patients = new ConcurrentHashMap<>();

//    private static final IParser parser;

    static {
        patients.put(java.lang.String.valueOf(counter), createPatient("Van Houte"));
        patients.put(java.lang.String.valueOf(counter), createPatient("Agnes"));
        for (int i = 0; i < 20; i++) {
            patients.put(java.lang.String.valueOf(counter), createPatient("Random Patient " + counter));
        }
//        parser = fhirContext.newJsonParser();
//        parser.setPrettyPrint(true);
    }

    @Override
    public JAXBElement<Patient> getPatient(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createPatient(patients.get(java.lang.String.valueOf(id)));
//        return patients.get(java.lang.String.valueOf(id));
    }

    @Override
    public JAXBElement<Patient> postPatient(JAXBElement<Patient> patient) {
        ObjectFactory objectFactory = new ObjectFactory();
        patients.put(java.lang.String.valueOf(counter), patient.getValue());
        return objectFactory.createPatient(Patient.patientBuilder().build());
//        return objectFactory.createPatient(patients.get(java.lang.String.valueOf(counter)));
    }


    private static Patient createPatient(java.lang.String name) {
        var patient = Patient.patientBuilder()
                .withId(Id.idBuilder()
                        .withId(java.lang.String.valueOf(counter))
                        .build())
                .addName(HumanName.humanNameBuilder()
                        .withFamily(String.stringBuilder()
                                .withValue(name)
                                .build())
                        .build())
                .build();
        counter++;
        return patient;
    }
}
