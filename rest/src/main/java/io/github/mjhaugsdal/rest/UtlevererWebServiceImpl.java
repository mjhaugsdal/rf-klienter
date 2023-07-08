package io.github.mjhaugsdal.rest;

import jakarta.ws.rs.NotAllowedException;
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


public class UtlevererWebServiceImpl implements UtlevererWebService {

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM10(io.github.mjhaugsdal.rest.types.utleverer.M10 m10Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM253(io.github.mjhaugsdal.rest.types.utleverer.M253 m253Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.M272 utlevererWebServiceM271(io.github.mjhaugsdal.rest.types.utleverer.M271 m271Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.utleverer.M272();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceVerify(io.github.mjhaugsdal.rest.types.utleverer.MV mvElement) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM252(io.github.mjhaugsdal.rest.types.utleverer.M252 m252Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.M94 utlevererWebServiceM93(io.github.mjhaugsdal.rest.types.utleverer.M93 m93Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.M94();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.M92 utlevererWebServiceM91(io.github.mjhaugsdal.rest.types.utleverer.M91 m91Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.M92();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.AppRec utlevererWebServiceM3(io.github.mjhaugsdal.rest.types.utleverer.M3 m3Element) {
        return new io.github.mjhaugsdal.rest.types.utleverer.AppRec();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.M912 utlevererWebServiceM911(io.github.mjhaugsdal.rest.types.utleverer.M911 m911Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.utleverer.M912();
    }

    @Override
    public io.github.mjhaugsdal.rest.types.utleverer.M922 utlevererWebServiceM921(io.github.mjhaugsdal.rest.types.utleverer.M921 m921Element) throws io.github.mjhaugsdal.rest.types.utleverer.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.utleverer.M922();
    }


    @Override
    public AppRec utlevererWebServiceM10(M10 m10Element) {
        throw new NotAllowedException("");
    }

    @Override
    public AppRec utlevererWebServiceM253(M253 m253Element) {
        throw new NotAllowedException("");
    }

    @Override
    public M272 utlevererWebServiceM271(M271 m271Element) throws AppRecFault_Exception {
        throw new NotAllowedException("");
    }

    @Override
    public AppRec utlevererWebServiceVerify(MV mvElement) {
        throw new NotAllowedException("");
    }

    @Override
    public AppRec utlevererWebServiceM252(M252 m252Element) {
        throw new NotAllowedException("");
    }

    @Override
    public M94 utlevererWebServiceM93(M93 m93Element) {
        throw new NotAllowedException("");
    }

    @Override
    public M92 utlevererWebServiceM91(M91 m91Element) {
        throw new NotAllowedException("");
    }

    @Override
    public AppRec utlevererWebServiceM3(M3 m3Element) {
        throw new NotAllowedException("");
    }

    @Override
    public M912 utlevererWebServiceM911(M911 m911Element) throws AppRecFault_Exception {
        throw new NotAllowedException("");
    }

    @Override
    public M922 utlevererWebServiceM921(M921 m921Element) throws AppRecFault_Exception {
        throw new NotAllowedException("");
    }
}
