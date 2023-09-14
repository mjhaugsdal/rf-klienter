
package io.github.mjhaugsdal.rest.types.rekvirent;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for M9_11 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="M9_11"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://rekvirent.webservices.reseptformidleren.ergo.no/types/}dokument"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "M9_11", namespace = "http://rekvirent.webservices.reseptformidleren.ergo.no/types/", propOrder = {
    "dokument"
})
@XmlRootElement(namespace = "http://rekvirent.webservices.reseptformidleren.ergo.no/types/")
public class M911 {

    @XmlElement(namespace = "http://rekvirent.webservices.reseptformidleren.ergo.no/types/", required = true)
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
