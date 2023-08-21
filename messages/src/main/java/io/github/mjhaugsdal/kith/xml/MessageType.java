package io.github.mjhaugsdal.kith.xml;


import no.kith.xmlstds.msghead._2006_05_24.CS;

public enum MessageType {

    M9NA1("Forespørsel om reseptliste fra nettkunde", "ERM9NA1"),
    M9NA2("Reseptliste nettkunde", "ERM9NA2"),
    M9NA3("Forespørsel om utvidet informasjon på resept fra nettkunde", "ERM9NA3"),
    M9NA4("Utvidet informasjon om resept nettkunde", "ERM9NA4");

    public CS getCS() {
        return cs;
    }

    private final CS cs;

    MessageType(String dn, String v) {
        this.cs = CS.CSBuilder().withDn(dn).withV(v).build();
    }
}
