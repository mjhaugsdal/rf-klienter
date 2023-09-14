
package io.github.mjhaugsdal.rest.types.utleverer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://utleverer.webservices.reseptformidleren.ergo.no/types/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppRec", propOrder = {
        "dokument"
})
public class AppRec {

    @XmlElement(namespace = "http://utleverer.webservices.reseptformidleren.ergo.no/types/", required = true)
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
