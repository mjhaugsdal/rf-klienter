package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.types.na.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.na.M9Na1;
import io.github.mjhaugsdal.rest.types.na.M9Na3;
import io.github.mjhaugsdal.rest.types.na.MV;
import io.quarkus.test.junit.QuarkusTest;
import org.awaitility.Awaitility;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@QuarkusTest
class NaWebServiceImplTest {


    @RestClient
    NaWebService naWebService;

    @Test
    void naWebServiceVerify() {
        naWebService.naWebServiceVerify(new MV());
    }

    @Test
    void naWebServiceM9Na1() {
        try {
//            Awaitility.await().pollDelay(Duration.ofMinutes(5)).timeout(Duration.ofMinutes(10)).untilAsserted(() -> Assertions.assertTrue(true));
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