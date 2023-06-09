package io.github.mjhaugsdal.soap.service;

import io.github.mjhaugsdal.soap.SoapTestConfiguration;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@Import(SoapTestConfiguration.class)
class NaWebServiceTest {

    @Autowired
    NAWeb naWeb;

    static String testMessage = "Hello world!";

    @Test
    void naWebServiceVerify() {
        var message = new MV();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = naWeb.naWebServiceVerify(message);
        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void naWebServiceM9Na1() throws AppRecFault_Exception {
        var message = new M9Na1();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = naWeb.naWebServiceM9Na1(message);
        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }

    @Test
    void naWebServiceM9Na3() throws AppRecFault_Exception {
        var message = new M9Na3();
        message.setDokument(testMessage.getBytes(StandardCharsets.UTF_8));
        var apprec = naWeb.naWebServiceM9Na3(message);
        Assertions.assertEquals(testMessage, new String((byte[]) apprec.getDokument()));
    }
}