
package fr.unice.polytech.isa.polyevent.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour organisateur complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="organisateur">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mail" type="{http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement}mail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organisateur", propOrder = {
    "mail"
})
public class Organisateur {

    protected Mail mail;

    /**
     * Obtient la valeur de la propri�t� mail.
     * 
     * @return
     *     possible object is
     *     {@link Mail }
     *     
     */
    public Mail getMail() {
        return mail;
    }

    /**
     * D�finit la valeur de la propri�t� mail.
     * 
     * @param value
     *     allowed object is
     *     {@link Mail }
     *     
     */
    public void setMail(Mail value) {
        this.mail = value;
    }

}
