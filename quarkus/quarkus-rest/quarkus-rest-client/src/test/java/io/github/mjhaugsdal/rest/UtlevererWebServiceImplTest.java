package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.utleverer.M10;
import io.github.mjhaugsdal.rest.types.utleverer.M252;
import io.github.mjhaugsdal.rest.types.utleverer.M253;
import io.github.mjhaugsdal.rest.types.utleverer.M271;
import io.github.mjhaugsdal.rest.types.utleverer.M3;
import io.github.mjhaugsdal.rest.types.utleverer.M91;
import io.github.mjhaugsdal.rest.types.utleverer.M911;
import io.github.mjhaugsdal.rest.types.utleverer.M921;
import io.github.mjhaugsdal.rest.types.utleverer.M93;
import io.github.mjhaugsdal.rest.types.utleverer.MV;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UtlevererWebServiceImplTest {


    @RestClient
    UtlevererWebService utlevererWebService;

    @Test
    void utlevererWebServiceM10() {
        utlevererWebService.utlevererWebServiceM10(new M10());
    }

    @Test
    void utlevererWebServiceM253() {
        utlevererWebService.utlevererWebServiceM253(new M253());
    }

    @Test
    void utlevererWebServiceM271() throws AppRecFault_Exception {
        utlevererWebService.utlevererWebServiceM271(new M271());
    }

    @Test
    void utlevererWebServiceVerify() {
        utlevererWebService.utlevererWebServiceVerify(new MV());
    }

    @Test
    void utlevererWebServiceM252() {
        utlevererWebService.utlevererWebServiceM252(new M252());
    }

    @Test
    void utlevererWebServiceM93() {
        utlevererWebService.utlevererWebServiceM93(new M93());
    }

    @Test
    void utlevererWebServiceM91() {
        utlevererWebService.utlevererWebServiceM91(new M91());
    }

    @Test
    void utlevererWebServiceM3() {
        utlevererWebService.utlevererWebServiceM3(new M3());
    }

    @Test
    void utlevererWebServiceM911() throws AppRecFault_Exception {
        utlevererWebService.utlevererWebServiceM911(new M911());
    }

    @Test
    void utlevererWebServiceM921() throws AppRecFault_Exception {
        utlevererWebService.utlevererWebServiceM921(new M921());
    }
}