
package io.github.mjhaugsdal.rest.types.utleverer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(namespace = "http://utleverer.webservices.reseptformidleren.ergo.no/types/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppRecFault", propOrder = {
        "dokument",
        "message"
})
public class AppRecFault {

    @XmlElement(namespace = "", required = true)
    protected Object dokument;
    @XmlElement(namespace = "")
    protected String message;

    /**
     * Gets the value of the dokument property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getDokument() {
        return dokument;
    }

    /**
     * Sets the value of the dokument property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setDokument(Object value) {
        this.dokument = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
