package io.github.mjhaugsdal.kith.xml;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
    static XMLGregorianCalendar getXmlgregorianCalendar() {
        XMLGregorianCalendar xmlDate;
        var gc = new GregorianCalendar();
        gc.setTime(new Date());
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return xmlDate;
    }

}
