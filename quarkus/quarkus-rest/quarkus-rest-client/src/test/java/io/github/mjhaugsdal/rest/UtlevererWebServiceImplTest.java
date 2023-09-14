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
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UtlevererWebServiceImplTest {


    @RestClient
    UtlevererWebService utlevererWebService;

    static String testMessage = "Hello world!";

    @Test
    void utlevererWebServiceM10() {
        var fagmelding = new M10();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM10(fagmelding);
    }

    @Test
    void utlevererWebServiceM253() {
        var fagmelding = new M253();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM253(fagmelding);
    }

    @Test
    void utlevererWebServiceM271() throws AppRecFault_Exception {
        var fagmelding = new M271();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM271(fagmelding);
    }

    @Test
    void utlevererWebServiceVerify() {
        var fagmelding = new MV();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceVerify(fagmelding);
    }

    @Test
    void utlevererWebServiceM252() {
        var fagmelding = new M252();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM252(fagmelding);
    }

    @Test
    void utlevererWebServiceM93() {
        var fagmelding = new M93();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM93(fagmelding);
    }

    @Test
    void utlevererWebServiceM91() {
        var fagmelding = new M91();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM91(fagmelding);
    }

    @Test
    void utlevererWebServiceM3() {
        var fagmelding = new M3();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM3(fagmelding);
    }

    @Test
    void utlevererWebServiceM911() throws AppRecFault_Exception {
        var fagmelding = new M911();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM911(fagmelding);
    }

    @Test
    void utlevererWebServiceM921() throws AppRecFault_Exception {
        var fagmelding = new M921();
        fagmelding.setDokument(testMessage.getBytes());
        utlevererWebService.utlevererWebServiceM921(fagmelding);
    }
}