package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.na.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.na.M9Na1;
import io.github.mjhaugsdal.rest.types.na.M9Na3;
import io.github.mjhaugsdal.rest.types.na.MV;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(CustomResource.class)
class NaWebServiceImplTest {


    @RestClient
    NaWebService naWebService;

    String testMessage = "Hello world!";

    @Test
    void naWebServiceVerify() {
        var mv = new MV();
        mv.setDokument(testMessage.getBytes());
        naWebService.naWebServiceVerify(mv);
    }

    @Test
    void naWebServiceM9Na1() {
        try {
            var m9na1 = new M9Na1();
            m9na1.setDokument(testMessage.getBytes());
            naWebService.naWebServiceM9Na1(m9na1);
        } catch (AppRecFault_Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void naWebServiceM9Na3() {
        try {
            var m9na3 = new M9Na3();
            m9na3.setDokument(testMessage.getBytes());
            naWebService.naWebServiceM9Na3(m9na3);
        } catch (AppRecFault_Exception e) {
            throw new RuntimeException(e);
        }

    }
}