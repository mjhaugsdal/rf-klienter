package io.github.mjhaugsdal.rest.impl;

import jakarta.jws.WebService;
import no.ergo.reseptformidleren.webservices.utleverer.AppRec;
import no.ergo.reseptformidleren.webservices.utleverer.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.utleverer.M10;
import no.ergo.reseptformidleren.webservices.utleverer.M252;
import no.ergo.reseptformidleren.webservices.utleverer.M253;
import no.ergo.reseptformidleren.webservices.utleverer.M271;
import no.ergo.reseptformidleren.webservices.utleverer.M272;
import no.ergo.reseptformidleren.webservices.utleverer.M3;
import no.ergo.reseptformidleren.webservices.utleverer.M91;
import no.ergo.reseptformidleren.webservices.utleverer.M911;
import no.ergo.reseptformidleren.webservices.utleverer.M912;
import no.ergo.reseptformidleren.webservices.utleverer.M92;
import no.ergo.reseptformidleren.webservices.utleverer.M921;
import no.ergo.reseptformidleren.webservices.utleverer.M922;
import no.ergo.reseptformidleren.webservices.utleverer.M93;
import no.ergo.reseptformidleren.webservices.utleverer.M94;
import no.ergo.reseptformidleren.webservices.utleverer.MV;
import no.ergo.reseptformidleren.webservices.utleverer.UtlevererWeb;

@WebService
public class UtlevererWebServiceImpl implements UtlevererWeb {

    @Override
    public AppRec utlevererWebServiceM10(M10 m10Element) {
        var appRec = new AppRec();
        appRec.setDokument(m10Element.getDokument());
        return appRec;
    }

    @Override
    public AppRec utlevererWebServiceM253(M253 m253Element) {
        var appRec = new AppRec();
        appRec.setDokument(m253Element.getDokument());
        return appRec;
    }

    @Override
    public M272 utlevererWebServiceM271(M271 m271Element) throws AppRecFault_Exception {
        var m272 = new M272();
        m272.setDokument(m271Element.getDokument());
        return m272;
    }

    @Override
    public AppRec utlevererWebServiceVerify(MV mvElement) {
        var appRec = new AppRec();
        appRec.setDokument(mvElement.getDokument());
        return appRec;
    }

    @Override
    public AppRec utlevererWebServiceM252(M252 m252Element) {
        var appRec = new AppRec();
        appRec.setDokument(m252Element.getDokument());
        return appRec;
    }

    @Override
    public M94 utlevererWebServiceM93(M93 m93Element) {
        var m94 = new M94();
        m94.setDokument(m93Element.getDokument());
        return m94;
    }

    @Override
    public M92 utlevererWebServiceM91(M91 m91Element) {
        var m92 = new M92();
        m92.setDokument(m91Element.getDokument());
        return m92;
    }

    @Override
    public AppRec utlevererWebServiceM3(M3 m3Element) {
        var appRec = new AppRec();
        appRec.setDokument(m3Element.getDokument());
        return appRec;
    }

    @Override
    public M912 utlevererWebServiceM911(M911 m911Element) throws AppRecFault_Exception {
        var m912 = new M912();
        m912.setDokument(m911Element.getDokument());
        return m912;
    }

    @Override
    public M922 utlevererWebServiceM921(M921 m921Element) throws AppRecFault_Exception {
        var m922 = new M922();
        m922.setDokument(m921Element.getDokument());
        return m922;
    }
}
