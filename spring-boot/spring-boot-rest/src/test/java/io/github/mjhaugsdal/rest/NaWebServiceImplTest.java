package io.github.mjhaugsdal.rest;

import io.github.mjhaugsdal.rest.configuration.RestTestConfiguration;
import io.github.mjhaugsdal.rest.types.na.AppRecFault_Exception;
import io.github.mjhaugsdal.rest.types.na.M9Na1;
import io.github.mjhaugsdal.rest.types.na.M9Na3;
import io.github.mjhaugsdal.rest.types.na.MV;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Duration;

@SpringBootTest
@Import({RestTestConfiguration.class})
class NaWebServiceImplTest {

    @Autowired
    NaWebService naWebService;

    @Test
    void naWebServiceVerify() {
        naWebService.naWebServiceVerify(new MV());
    }

    @Test
    void naWebServiceM9Na1() {
        try {
            naWebService.naWebServiceM9Na1(new M9Na1());
            Awaitility.await().timeout(Duration.ofMinutes(10)).pollDelay(Duration.ofMinutes(5)).until(() -> true);
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