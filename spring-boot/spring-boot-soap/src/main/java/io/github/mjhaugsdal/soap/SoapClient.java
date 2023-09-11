package io.github.mjhaugsdal.soap;

import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;

public record SoapClient(NAWeb naWeb, RekvirentWeb rekvirentWeb, UtlevererWeb utlevererWeb) {

}
