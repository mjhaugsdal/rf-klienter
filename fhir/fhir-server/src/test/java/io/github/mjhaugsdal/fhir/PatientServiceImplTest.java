package io.github.mjhaugsdal.fhir;

import org.hl7.fhir.AdministrativeGender;
import org.hl7.fhir.AdministrativeGenderEnum;
import org.hl7.fhir.ObjectFactory;
import org.hl7.fhir.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
@Import(TestConfiguration.class)
class PatientServiceImplTest {


    @Autowired
    PatientService patientService;

    @Test
    void getPatient() {
        var test =patientService.getPatient(1);
        System.out.println("");
    }

    @Test
    void createPatient() throws IOException {

        var patient = Patient.patientBuilder()
                .withGender(AdministrativeGender.administrativeGenderBuilder()
                        .withValue(AdministrativeGenderEnum.MALE)
                        .build())
                .build();

        ObjectFactory objectFactory = new ObjectFactory();
        var pat = objectFactory.createPatient(patient);

        var ret = patientService.postPatient(pat);
        System.out.println();
//        try (var is = (InputStream) ret.getEntity()) {
//            var patient2 = (Patient) parser.parseResource(is);
//            Assertions.assertTrue(patient2.getGender() == Enumerations.AdministrativeGender.MALE);
//        }
    }
}