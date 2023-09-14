package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.na.M9Na3;
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
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
//@Import({RestTestConfiguration.class})
class RekvirentWebServiceImplTest {

    @RestClient
    RekvirentWebService rekvirentWebService;

    static String testMessage = "Hello world!";

    @Test
    void rekvirentWebServiceM921() throws AppRecFault_Exception {
        var fagmelding = new M921();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM921(fagmelding);
    }

    @Test
    void rekvirentWebServiceM911() throws AppRecFault_Exception {
        var fagmelding = new M911();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM911(fagmelding);
    }

    @Test
    void rekvirentWebServiceM5() {
        var fagmelding = new M5();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM5(fagmelding);
    }

    @Test
    void rekvirentWebServiceM911Kj() throws AppRecFault_Exception {
        var fagmelding = new M911Kj();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM911Kj(fagmelding);
    }

    @Test
    void rekvirentWebServiceM97() throws AppRecFault_Exception {
        var fagmelding = new M97();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM97(fagmelding);
    }

    @Test
    void rekvirentWebServiceM95Kj() throws AppRecFault_Exception {
        var fagmelding = new M95Kj();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM95Kj(fagmelding);
    }

    @Test
    void rekvirentWebServiceM95() {
        var fagmelding = new M95();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM95(fagmelding);
    }

    @Test
    void rekvirentWebServiceM251() {
        var fagmelding = new M251();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM251(fagmelding);
    }

    @Test
    void rekvirentWebServiceM271() throws AppRecFault_Exception {
        var fagmelding = new M271();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM271(fagmelding);
    }

    @Test
    void rekvirentWebServiceM1() {
        var fagmelding = new M1();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM1(fagmelding);
    }

    @Test
    void rekvirentWebServiceM241() {
        var fagmelding = new M241();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM241(fagmelding);
    }

    @Test
    void rekvirentWebServiceM41() {
        var fagmelding = new M241();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceM241(fagmelding);
    }

    @Test
    void rekvirentWebServiceVerify() {
        var fagmelding = new MV();
        fagmelding.setDokument(testMessage.getBytes());
        rekvirentWebService.rekvirentWebServiceVerify(fagmelding);
    }
}