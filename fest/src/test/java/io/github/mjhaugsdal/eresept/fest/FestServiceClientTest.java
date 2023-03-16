package io.github.mjhaugsdal.eresept.fest;

import no.slv._201410325.FilterEnum;

import java.io.IOException;

class FestServiceClientTest {

    @org.junit.jupiter.api.Test
    void hentFest() throws IOException {

        FestServiceClient festServiceClient = new FestServiceClient();
        var fest = festServiceClient.hentFest("https://fest.legemiddelverket.no/Fest/FestService251.svc", FilterEnum.BANDASJIST);
        var dato = fest.getHentetDato();
    }
}