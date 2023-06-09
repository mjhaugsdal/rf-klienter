package io.github.mjhaugsdal.soap;

import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;

public class SoapClient {


    public UtlevererWeb getUtlevererWeb() {
        return utlevererWeb;
    }

    public RekvirentWeb getRekvirentWeb() {
        return rekvirentWeb;
    }

    public NAWeb getNaWeb() {
        return naWeb;
    }

    private final UtlevererWeb utlevererWeb;
    private final RekvirentWeb rekvirentWeb;
    private final NAWeb naWeb;

    public SoapClient(NAWeb naWeb, RekvirentWeb rekvirentWeb, UtlevererWeb utlevererWeb) {
        this.naWeb = naWeb;
        this.rekvirentWeb = rekvirentWeb;
        this.utlevererWeb = utlevererWeb;
    }
}
