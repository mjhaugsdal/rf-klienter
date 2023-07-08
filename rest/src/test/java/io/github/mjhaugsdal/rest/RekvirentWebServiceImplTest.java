package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.configuration.RestTestConfiguration;
import io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.rekvirent.M1;
import io.github.mjhaugsdal.rest.types.rekvirent.M241;
import io.github.mjhaugsdal.rest.types.rekvirent.M251;
import io.github.mjhaugsdal.rest.types.rekvirent.M271;
import io.github.mjhaugsdal.rest.types.rekvirent.M5;
import io.github.mjhaugsdal.rest.types.rekvirent.M911;
import io.github.mjhaugsdal.rest.types.rekvirent.M911Kj;
import io.github.mjhaugsdal.rest.types.rekvirent.M921;
import io.github.mjhaugsdal.rest.types.rekvirent.M95;
import io.github.mjhaugsdal.rest.types.rekvirent.M95Kj;
import io.github.mjhaugsdal.rest.types.rekvirent.M97;
import io.github.mjhaugsdal.rest.types.rekvirent.MV;
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
    void rekvirentWebServiceM921() throws AppRecFault_Exception {
        rekvirentWebService.rekvirentWebServiceM921(new M921());
    }

    @Test
    void rekvirentWebServiceM911() throws AppRecFault_Exception {
        rekvirentWebService.rekvirentWebServiceM911(new M911());
    }

    @Test
    void rekvirentWebServiceM5() {
        rekvirentWebService.rekvirentWebServiceM5(new M5());
    }

    @Test
    void rekvirentWebServiceM911Kj() throws AppRecFault_Exception {
        rekvirentWebService.rekvirentWebServiceM911Kj(new M911Kj());
    }

    @Test
    void rekvirentWebServiceM97() throws AppRecFault_Exception {
        rekvirentWebService.rekvirentWebServiceM97(new M97());
    }

    @Test
    void rekvirentWebServiceM95Kj() throws AppRecFault_Exception {
        rekvirentWebService.rekvirentWebServiceM95Kj(new M95Kj());
    }

    @Test
    void rekvirentWebServiceM95() {
        rekvirentWebService.rekvirentWebServiceM95(new M95());
    }

    @Test
    void rekvirentWebServiceM251() {
        rekvirentWebService.rekvirentWebServiceM251(new M251());
    }

    @Test
    void rekvirentWebServiceM271() throws AppRecFault_Exception {
        rekvirentWebService.rekvirentWebServiceM271(new M271());
    }

    @Test
    void rekvirentWebServiceM1() {
        rekvirentWebService.rekvirentWebServiceM1(new M1());
    }

    @Test
    void rekvirentWebServiceM241() {
        rekvirentWebService.rekvirentWebServiceM241(new M241());
    }

    @Test
    void rekvirentWebServiceM41() {
        rekvirentWebService.rekvirentWebServiceM241(new M241());
    }

    @Test
    void rekvirentWebServiceVerify() {
        rekvirentWebService.rekvirentWebServiceVerify(new MV());
    }
}