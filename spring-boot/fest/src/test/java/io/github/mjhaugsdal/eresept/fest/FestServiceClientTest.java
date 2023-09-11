package io.github.mjhaugsdal.eresept.fest;

import io.github.mjhaugsdal.eresept.fest.configuration.FestTestConfiguration;
import no.slv._201410325.FestService251;
import no.slv._201410325.FilterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.IOException;

@SpringBootTest
@Import({FestTestConfiguration.class})
class FestServiceClientTest {

    @Value("${soap.address}")
    String address;

    @Autowired
    FestService251 festService251;

    @org.junit.jupiter.api.Test
    void hentFest() throws IOException {
        festService251.getM30(FilterEnum.BANDASJIST, null);
    }
}