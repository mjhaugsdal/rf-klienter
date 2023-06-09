package io.github.mjhaugsdal.soap.service;

import no.ergo.reseptformidleren.webservices.rekvirent.AppRec;
import no.ergo.reseptformidleren.webservices.rekvirent.AppRecFault;
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

import java.nio.charset.StandardCharsets;

public class RekvirentWebservice implements RekvirentWeb {
    @Override
    public M922 rekvirentWebServiceM921(M921 m921Element) throws AppRecFault_Exception {
        var m922 = new M922();
        m922.setDokument(m921Element.getDokument());
        return m922;
    }

    @Override
    public M912 rekvirentWebServiceM911(M911 m911Element) throws AppRecFault_Exception {
        var m912 = new M912();
        m912.setDokument(m911Element.getDokument());
        return m912;
    }

    @Override
    public AppRec rekvirentWebServiceM5(M5 m911Element) {
        var appRec = new AppRec();
        appRec.setDokument(m911Element.getDokument());
        return appRec;
    }

    @Override
    public M912Kj rekvirentWebServiceM911Kj(M911Kj m911KjElement) throws AppRecFault_Exception {
        var m912Kj = new M912Kj();
        m912Kj.setDokument(m911KjElement.getDokument());
        return m912Kj;
    }

    @Override
    public M98 rekvirentWebServiceM97(M97 m97Element) throws AppRecFault_Exception {
        var msg = (byte[])m97Element.getDokument();
        var str = new String(msg, StandardCharsets.UTF_8);
        if(str.contains("fault")) {
            var appRec = new AppRecFault();
            appRec.setDokument(m97Element.getDokument());
            throw new AppRecFault_Exception("", appRec);
        }
        var m98 = new M98();
        m98.setDokument(m97Element.getDokument());
        return m98;
    }

    @Override
    public M96Kj rekvirentWebServiceM95Kj(M95Kj m95KjElement) throws AppRecFault_Exception {
        var m96Kj = new M96Kj();
        m96Kj.setDokument(m95KjElement.getDokument());
        return m96Kj;
    }

    @Override
    public M96 rekvirentWebServiceM95(M95 m95Element) {
        var m96 = new M96();
        m96.setDokument(m95Element.getDokument());
        return m96;
    }

    @Override
    public AppRec rekvirentWebServiceM251(M251 m251Element) {
        var appRec = new AppRec();
        appRec.setDokument(m251Element.getDokument());
        return appRec;
    }

    @Override
    public M272 rekvirentWebServiceM271(M271 m271Element) throws AppRecFault_Exception {
        var m272 = new M272();
        m272.setDokument(m271Element.getDokument());
        return m272;
    }

    @Override
    public AppRec rekvirentWebServiceM1(M1 m1Element) {
        var appRec = new AppRec();
        appRec.setDokument(m1Element.getDokument());
        return appRec;
    }

    @Override
    public M242 rekvirentWebServiceM241(M241 m241Element) {
        var m242 = new M242();
        m242.setDokument(m241Element.getDokument());
        return m242;
    }

    @Override
    public M42 rekvirentWebServiceM41(M41 m41Element) {
        var m42 = new M42();
        m42.setDokument(m41Element.getDokument());
        return m42;
    }

    @Override
    public AppRec rekvirentWebServiceVerify(MV mvElement) {
        var appRec = new AppRec();
        appRec.setDokument(mvElement.getDokument());
        return appRec;
    }
}
