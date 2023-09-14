package io.github.mjhaugsdal.rest.types.na;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://na.webservices.reseptformidleren.ergo.no/types/")
@XmlType(name = "AppRecFault_Exception", namespace = "http://na.webservices.reseptformidleren.ergo.no/types/")
public class AppRecFault_Exception extends Exception {

    private AppRecFault faultInfo;

    public AppRecFault_Exception() {
        super();
    }

    public AppRecFault_Exception(String message) {
        super(message);
    }

    public AppRecFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public AppRecFault_Exception(String message, AppRecFault appRecFault) {
        super(message);
        this.faultInfo = appRecFault;
    }

    public AppRecFault_Exception(String message, AppRecFault appRecFault, java.lang.Throwable cause) {
        super(message, cause);
        this.faultInfo = appRecFault;
    }

    public AppRecFault getFaultInfo() {
        return this.faultInfo;
    }
}