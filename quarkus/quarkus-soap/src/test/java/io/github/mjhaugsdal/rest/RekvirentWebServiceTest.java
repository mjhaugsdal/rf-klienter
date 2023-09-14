package io.github.mjhaugsdal.rest;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import no.ergo.reseptformidleren.webservices.rekvirent.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.rekvirent.M1;
import no.ergo.reseptformidleren.webservices.rekvirent.M241;
import no.ergo.reseptformidleren.webservices.rekvirent.M251;
import no.ergo.reseptformidleren.webservices.rekvirent.M271;
import no.ergo.reseptformidleren.webservices.rekvirent.M41;
import no.ergo.reseptformidleren.webservices.rekvirent.M5;
import no.ergo.reseptformidleren.webservices.rekvirent.M911;
import no.ergo.reseptformidleren.webservices.rekvirent.M911Kj;
import no.ergo.reseptformidleren.webservices.rekvirent.M921;
import no.ergo.reseptformidleren.webservices.rekvirent.M95;
import no.ergo.reseptformidleren.webservices.rekvirent.M95Kj;
import no.ergo.reseptformidleren.webservices.rekvirent.M97;
import no.ergo.reseptformidleren.webservices.rekvirent.MV;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

@QuarkusTest
public class RekvirentWebServiceTest {

    @Inject
    @CXFClient("rekvirentWeb") //
    RekvirentWeb rekvirentWeb;

    static String testMessage = "Hello world!";

    @Test
    void rekvirentWebServiceM921() throws AppRecFault_Exception {
        var message = new M921();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM921(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM911() throws AppRecFault_Exception {
        var message = new M911();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM911(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM5() {
        var message = new M5();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM5(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM911Kj() throws AppRecFault_Exception {
        var message = new M911Kj();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM911Kj(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM97() throws AppRecFault_Exception {
        var message = new M97();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM97(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM97Fault() {
        try {
            var message = new M97();
            message.setDokument("fault".getBytes(StandardCharsets.UTF_8));
            rekvirentWeb.rekvirentWebServiceM97(message);
        } catch (AppRecFault_Exception e) {
            var dokument = new String((byte[]) e.getFaultInfo().getDokument());
//            Assertions.assertEquals("fault", dokument);
        }
    }

    @Test
    void rekvirentWebServiceM95Kj() throws AppRecFault_Exception {
        var message = new M95Kj();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM95Kj(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM95() {
        var message = new M95();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM95(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM251() {
        var message = new M251();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM251(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM271() throws AppRecFault_Exception {
        var message = new M271();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM271(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM1() {
        var message = new M1();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM1(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM241() {
        var message = new M241();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM241(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceM41() {
        var message = new M41();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceM41(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void rekvirentWebServiceVerify() {
        var message = new MV();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = rekvirentWeb.rekvirentWebServiceVerify(message);
//        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }
}
