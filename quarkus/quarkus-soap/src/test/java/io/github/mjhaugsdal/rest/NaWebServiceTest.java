package io.github.mjhaugsdal.rest;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NaWebServiceTest {

    @Inject
    @CXFClient("naWeb") //
    NAWeb naWebService;


    @Test
    void naWebServiceVerify() {
        naWebService.naWebServiceVerify(new MV());
    }

    @Test
    void naWebServiceM9Na1() {
        try {
            naWebService.naWebServiceM9Na1(new M9Na1());
        } catch (AppRecFault_Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void naWebServiceM9Na3() {
        try {
            naWebService.naWebServiceM9Na3(new M9Na3());
        } catch (AppRecFault_Exception e) {
            throw new RuntimeException(e);
        }

    }
}
