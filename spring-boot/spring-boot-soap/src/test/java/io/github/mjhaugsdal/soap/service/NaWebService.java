package io.github.mjhaugsdal.soap.service;

import io.github.mjhaugsdal.kith.xml.MessageType;
import io.github.mjhaugsdal.kith.xml.MsgHeadBuilder;
import io.github.mjhaugsdal.kith.xml.XMLUtil;
import jakarta.annotation.Resource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.ws.WebServiceContext;
import no.ergo.reseptformidleren.webservices.na.AppRec;
import no.ergo.reseptformidleren.webservices.na.AppRecFault;
import no.ergo.reseptformidleren.webservices.na.AppRecFault_Exception;
import no.ergo.reseptformidleren.webservices.na.M9Na1;
import no.ergo.reseptformidleren.webservices.na.M9Na2;
import no.ergo.reseptformidleren.webservices.na.M9Na3;
import no.ergo.reseptformidleren.webservices.na.M9Na4;
import no.ergo.reseptformidleren.webservices.na.MV;
import no.ergo.reseptformidleren.webservices.na.NAWeb;
import no.kith.xmlstds.CS;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.eresept.m9na4._2016_06_06.M9NA4;
import no.kith.xmlstds.msghead._2006_05_24.Document;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;
import no.kith.xmlstds.msghead._2006_05_24.RefDoc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NaWebService implements NAWeb {

    @Resource
    WebServiceContext context;

    @Override
    public AppRec naWebServiceVerify(MV parameters) {
        var appRec = new AppRec();
        appRec.setDokument(parameters.getDokument());
        return appRec;
    }

    @Override
    public M9Na2 naWebServiceM9Na1(M9Na1 parameters) throws AppRecFault_Exception {

        var xmlUtil = new XMLUtil(MsgHead.class, M9NA1.class, M9NA2.class);
        try {
            var msgHead = (MsgHead) xmlUtil.unmarshall(new String((byte[]) parameters.getDokument()), StandardCharsets.UTF_8);
        } catch (JAXBException e) {
            throw new AppRecFault_Exception(e.getMessage(), new AppRecFault());
        }

        try (var is = NaWebServiceTest.class.getClassLoader().getResourceAsStream("M9NA2_eks.xml");) {
            assert is != null;
            var m9na2 = (MsgHead) xmlUtil.unmarshall(new String(is.readAllBytes()), StandardCharsets.UTF_8);
            var m9na2Document = new M9Na2();
            m9na2Document.setDokument(xmlUtil.marshall(m9na2).getBytes(StandardCharsets.UTF_8));
            return m9na2Document;
        } catch (JAXBException | IOException e) {
            throw new AppRecFault_Exception(e.getMessage(), new AppRecFault());
        }
    }

    @Override
    public M9Na4 naWebServiceM9Na3(M9Na3 parameters) throws AppRecFault_Exception {
        var xmlUtil = new XMLUtil(MsgHead.class, M9NA3.class, M9NA4.class);
        try {
            var msgHead = (MsgHead) xmlUtil.unmarshall(new String((byte[]) parameters.getDokument()), StandardCharsets.UTF_8);
        } catch (JAXBException e) {
            throw new AppRecFault_Exception(e.getMessage(), new AppRecFault());
        }
        try (var is = NaWebServiceTest.class.getClassLoader().getResourceAsStream("M9NA4_eks.xml");) {
            assert is != null;
            var m9na4 = (MsgHead) xmlUtil.unmarshall(new String(is.readAllBytes()), StandardCharsets.UTF_8);
            var m9na4Document = new M9Na4();
            m9na4Document.setDokument(xmlUtil.marshall(m9na4).getBytes(StandardCharsets.UTF_8));
            return m9na4Document;
        } catch (JAXBException | IOException e) {
            throw new AppRecFault_Exception(e.getMessage(), new AppRecFault());
        }
    }
}
