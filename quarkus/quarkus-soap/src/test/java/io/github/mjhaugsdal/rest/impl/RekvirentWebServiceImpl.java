package io.github.mjhaugsdal.rest.impl;

import jakarta.jws.WebService;
import no.ergo.reseptformidleren.webservices.rekvirent.AppRec;
import no.ergo.reseptformidleren.webservices.rekvirent.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.rekvirent.M1;
import no.ergo.reseptformidleren.webservices.rekvirent.M241;
import no.ergo.reseptformidleren.webservices.rekvirent.M242;
import no.ergo.reseptformidleren.webservices.rekvirent.M251;
import no.ergo.reseptformidleren.webservices.rekvirent.M271;
import no.ergo.reseptformidleren.webservices.rekvirent.M272;
import no.ergo.reseptformidleren.webservices.rekvirent.M41;
import no.ergo.reseptformidleren.webservices.rekvirent.M42;
import no.ergo.reseptformidleren.webservices.rekvirent.M5;
import no.ergo.reseptformidleren.webservices.rekvirent.M911;
import no.ergo.reseptformidleren.webservices.rekvirent.M911Kj;
import no.ergo.reseptformidleren.webservices.rekvirent.M912;
import no.ergo.reseptformidleren.webservices.rekvirent.M912Kj;
import no.ergo.reseptformidleren.webservices.rekvirent.M921;
import no.ergo.reseptformidleren.webservices.rekvirent.M922;
import no.ergo.reseptformidleren.webservices.rekvirent.M95;
import no.ergo.reseptformidleren.webservices.rekvirent.M95Kj;
import no.ergo.reseptformidleren.webservices.rekvirent.M96;
import no.ergo.reseptformidleren.webservices.rekvirent.M96Kj;
import no.ergo.reseptformidleren.webservices.rekvirent.M97;
import no.ergo.reseptformidleren.webservices.rekvirent.M98;
import no.ergo.reseptformidleren.webservices.rekvirent.MV;
import no.ergo.reseptformidleren.webservices.rekvirent.RekvirentWeb;

@WebService
public class RekvirentWebServiceImpl implements RekvirentWeb {
    @Override
    public M922 rekvirentWebServiceM921(M921 m921Element) throws AppRecFault_Exception {
        return new M922();
    }

    @Override
    public M912 rekvirentWebServiceM911(M911 m911Element) throws AppRecFault_Exception {
        return new M912();
    }

    @Override
    public AppRec rekvirentWebServiceM5(M5 m5Element) {
        return new AppRec();
    }

    @Override
    public M912Kj rekvirentWebServiceM911Kj(M911Kj m911KjElement) throws AppRecFault_Exception {
        return new M912Kj();
    }

    @Override
    public M98 rekvirentWebServiceM97(M97 m97Element) throws AppRecFault_Exception {
        return new M98();
    }

    @Override
    public M96Kj rekvirentWebServiceM95Kj(M95Kj m95KjElement) throws AppRecFault_Exception {
        return new M96Kj();
    }

    @Override
    public M96 rekvirentWebServiceM95(M95 m95Element) {
        return new M96();
    }

    @Override
    public AppRec rekvirentWebServiceM251(M251 m251Element) {
        return new AppRec();
    }

    @Override
    public M272 rekvirentWebServiceM271(M271 m271Element) throws AppRecFault_Exception {
        return new M272();
    }

    @Override
    public AppRec rekvirentWebServiceM1(M1 m1Element) {
        return new AppRec();
    }

    @Override
    public M242 rekvirentWebServiceM241(M241 m241Element) {
        return new M242();
    }

    @Override
    public M42 rekvirentWebServiceM41(M41 m41Element) {
        return new M42();
    }

    @Override
    public AppRec rekvirentWebServiceVerify(MV mvElement) {
        return new AppRec();
    }
}
