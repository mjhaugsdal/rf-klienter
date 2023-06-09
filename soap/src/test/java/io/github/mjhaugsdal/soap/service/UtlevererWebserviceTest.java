package io.github.mjhaugsdal.soap.service;

import io.github.mjhaugsdal.soap.configuration.SoapTestConfiguration;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.ergo.reseptformidleren.webservices.utleverer.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.utleverer.M10;
import no.ergo.reseptformidleren.webservices.utleverer.M252;
import no.ergo.reseptformidleren.webservices.utleverer.M253;
import no.ergo.reseptformidleren.webservices.utleverer.M271;
import no.ergo.reseptformidleren.webservices.utleverer.M3;
import no.ergo.reseptformidleren.webservices.utleverer.M91;
import no.ergo.reseptformidleren.webservices.utleverer.M911;
import no.ergo.reseptformidleren.webservices.utleverer.M921;
import no.ergo.reseptformidleren.webservices.utleverer.M93;
import no.ergo.reseptformidleren.webservices.utleverer.MV;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@Import(SoapTestConfiguration.class)
class UtlevererWebserviceTest {

    @Autowired
    NAWeb naWeb;

    @Autowired
    UtlevererWeb utlevererWeb;


    static String testMessage = "Hello world!";

    @Test
    void utlevererWebServiceM10() {
        var message = new M10();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM10(message);
    }

    @Test
    void utlevererWebServiceM253() {
        var message = new M253();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM253(message);
    }

    @Test
    void utlevererWebServiceM271() throws AppRecFault_Exception {
        var message = new M271();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM271(message);
    }

    @Test
    void utlevererWebServiceVerify() {
        var message = new MV();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceVerify(message);
    }

    @Test
    void utlevererWebServiceM252() {
        var message = new M252();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM252(message);
    }

    @Test
    void utlevererWebServiceM93() {
        var message = new M93();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM93(message);
    }

    @Test
    void utlevererWebServiceM91() {
        var message = new M91();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM91(message);
    }

    @Test
    void utlevererWebServiceM3() {
        var message = new M3();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM3(message);
    }

    @Test
    void utlevererWebServiceM911() throws AppRecFault_Exception {
        var message = new M911();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM911(message);
    }

    @Test
    void utlevererWebServiceM921() throws AppRecFault_Exception {
        var message = new M921();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        utlevererWeb.utlevererWebServiceM921(message);
    }
}