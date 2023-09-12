package io.github.mjhaugsdal.rest.types.na;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "M9na3", propOrder = {
        "dokument"
})
@XmlRootElement
public class M9Na3 {

    @XmlElement(namespace = "http://na.webservices.reseptformidleren.ergo.no/types/", required = true)
    protected Object dokument;

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

}