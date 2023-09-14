package io.github.mjhaugsdal.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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

@Path("/")
public class RekvirentWebServiceImpl implements RekvirentWebService {

    @Override
    @Path("/m921")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M922 rekvirentWebServiceM921(io.github.mjhaugsdal.rest.types.rekvirent.M921 m921Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M922();
    }
    @Override
    @Path("/m911")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M912 rekvirentWebServiceM911(io.github.mjhaugsdal.rest.types.rekvirent.M911 m911Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M912();
    }
    @Override
    @Path("/m5")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM5(io.github.mjhaugsdal.rest.types.rekvirent.M5 m5Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }
    @Override
    @Path("/m911kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M912Kj rekvirentWebServiceM911Kj(io.github.mjhaugsdal.rest.types.rekvirent.M911Kj m911KjElement) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M912Kj();
    }
    @Override
    @Path("/m97")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M98 rekvirentWebServiceM97(io.github.mjhaugsdal.rest.types.rekvirent.M97 m97Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M98();
    }
    @Override
    @Path("/m95kj")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M96Kj rekvirentWebServiceM95Kj(io.github.mjhaugsdal.rest.types.rekvirent.M95Kj m95KjElement) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M96Kj();
    }
    @Override
    @Path("/m95")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M96 rekvirentWebServiceM95(io.github.mjhaugsdal.rest.types.rekvirent.M95 m95Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M96();
    }
    @Override
    @Path("/m251")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM251(io.github.mjhaugsdal.rest.types.rekvirent.M251 m251Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }
    @Override
    @Path("/m271")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M272 rekvirentWebServiceM271(io.github.mjhaugsdal.rest.types.rekvirent.M271 m271Element) throws io.github.mjhaugsdal.rest.types.rekvirent.AppRecFault_Exception {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M272();
    }
    @Override
    @Path("/m1")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceM1(io.github.mjhaugsdal.rest.types.rekvirent.M1 m1Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }
    @Override
    @Path("/m241")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M242 rekvirentWebServiceM241(io.github.mjhaugsdal.rest.types.rekvirent.M241 m241Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M242();
    }
    @Override
    @Path("/m41")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.M42 rekvirentWebServiceM41(io.github.mjhaugsdal.rest.types.rekvirent.M41 m41Element) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.M42();
    }
    @Override
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    @POST
    public io.github.mjhaugsdal.rest.types.rekvirent.AppRec rekvirentWebServiceVerify(io.github.mjhaugsdal.rest.types.rekvirent.MV mvElement) {
        return new io.github.mjhaugsdal.rest.types.rekvirent.AppRec();
    }
    @Override
    public M922 rekvirentWebServiceM921(M921 m921Element) throws AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
    @Override
    public M912 rekvirentWebServiceM911(M911 m911Element) throws AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
    @Override
    public AppRec rekvirentWebServiceM5(M5 m5Element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public M912Kj rekvirentWebServiceM911Kj(M911Kj m911KjElement) throws AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
    @Override
    public M98 rekvirentWebServiceM97(M97 m97Element) throws AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
    @Override
    public M96Kj rekvirentWebServiceM95Kj(M95Kj m95KjElement) throws AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
    @Override
    public M96 rekvirentWebServiceM95(M95 m95Element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public AppRec rekvirentWebServiceM251(M251 m251Element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public M272 rekvirentWebServiceM271(M271 m271Element) throws AppRecFault_Exception {
        throw new UnsupportedOperationException();
    }
    @Override
    public AppRec rekvirentWebServiceM1(M1 m1Element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public M242 rekvirentWebServiceM241(M241 m241Element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public M42 rekvirentWebServiceM41(M41 m41Element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public AppRec rekvirentWebServiceVerify(MV mvElement) {
        throw new UnsupportedOperationException();
    }
}
