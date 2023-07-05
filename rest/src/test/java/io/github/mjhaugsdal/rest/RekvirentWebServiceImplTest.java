package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.configuration.RestTestConfiguration;
import io.github.mjhaugsdal.rest.types.rekvirent.M1;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({RestTestConfiguration.class})
class RekvirentWebServiceImplTest {

    @Autowired
    RekvirentWebService rekvirentWebService;


    @Test
    void rekvirentWebServiceM921() {
        rekvirentWebService.rekvirentWebServiceM1(new M1());
    }

    @Test
    void rekvirentWebServiceM911() {
    }

    @Test
    void rekvirentWebServiceM5() {
    }

    @Test
    void rekvirentWebServiceM911Kj() {
    }

    @Test
    void rekvirentWebServiceM97() {
    }

    @Test
    void rekvirentWebServiceM95Kj() {
    }

    @Test
    void rekvirentWebServiceM95() {
    }

    @Test
    void rekvirentWebServiceM251() {
    }

    @Test
    void rekvirentWebServiceM271() {
    }

    @Test
    void rekvirentWebServiceM1() {
    }

    @Test
    void rekvirentWebServiceM241() {
    }

    @Test
    void rekvirentWebServiceM41() {
    }

    @Test
    void rekvirentWebServiceVerify() {
    }
}