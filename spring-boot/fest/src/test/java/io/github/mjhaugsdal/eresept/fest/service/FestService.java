package io.github.mjhaugsdal.eresept.fest.service;

import no.kith.xmlstds.eresept.m30._2014_12_01.FEST;
import no.slv._201410325.FestService251;
import no.slv._201410325.FilterEnum;
import no.slv._201410325.M30Response;

import javax.xml.datatype.XMLGregorianCalendar;

public class FestService implements FestService251 {
    @Override
    public M30Response getM30(FilterEnum filter, XMLGregorianCalendar incrementalDate) {
        var m30Response = new M30Response();
        m30Response.setM30Message(new FEST());
        return m30Response;
    }
}
