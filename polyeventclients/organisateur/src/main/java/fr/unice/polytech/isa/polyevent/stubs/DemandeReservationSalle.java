
package fr.unice.polytech.isa.polyevent.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour demandeReservationSalle complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="demandeReservationSalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeSalle" type="{http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement}typeSalle" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "demandeReservationSalle", propOrder = {
    "dateDebut",
    "dateFin",
    "nom",
    "typeSalle"
})
public class DemandeReservationSalle {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateDebut;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFin;
    protected String nom;
    @XmlSchemaType(name = "string")
    protected TypeSalle typeSalle;

    /**
     * Obtient la valeur de la propri�t� dateDebut.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDebut() {
        return dateDebut;
    }

    /**
     * D�finit la valeur de la propri�t� dateDebut.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDebut(XMLGregorianCalendar value) {
        this.dateDebut = value;
    }

    /**
     * Obtient la valeur de la propri�t� dateFin.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFin() {
        return dateFin;
    }

    /**
     * D�finit la valeur de la propri�t� dateFin.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFin(XMLGregorianCalendar value) {
        this.dateFin = value;
    }

    /**
     * Obtient la valeur de la propri�t� nom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * D�finit la valeur de la propri�t� nom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Obtient la valeur de la propri�t� typeSalle.
     * 
     * @return
     *     possible object is
     *     {@link TypeSalle }
     *     
     */
    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    /**
     * D�finit la valeur de la propri�t� typeSalle.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSalle }
     *     
     */
    public void setTypeSalle(TypeSalle value) {
        this.typeSalle = value;
    }

}
