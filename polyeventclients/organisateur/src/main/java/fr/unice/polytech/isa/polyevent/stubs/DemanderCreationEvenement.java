
package fr.unice.polytech.isa.polyevent.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour demanderCreationEvenement complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="demanderCreationEvenement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="organisateur" type="{http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement}organisateur" minOccurs="0"/>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date_debut" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="date_fin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="demande_reservations" type="{http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement}demandeReservationSalle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "demanderCreationEvenement", propOrder = {
    "organisateur",
    "nom",
    "dateDebut",
    "dateFin",
    "demandeReservations"
})
public class DemanderCreationEvenement {

    protected Organisateur organisateur;
    protected String nom;
    @XmlElement(name = "date_debut")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateDebut;
    @XmlElement(name = "date_fin")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFin;
    @XmlElement(name = "demande_reservations")
    protected List<DemandeReservationSalle> demandeReservations;

    /**
     * Obtient la valeur de la propri�t� organisateur.
     * 
     * @return
     *     possible object is
     *     {@link Organisateur }
     *     
     */
    public Organisateur getOrganisateur() {
        return organisateur;
    }

    /**
     * D�finit la valeur de la propri�t� organisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link Organisateur }
     *     
     */
    public void setOrganisateur(Organisateur value) {
        this.organisateur = value;
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
     * Gets the value of the demandeReservations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the demandeReservations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDemandeReservations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DemandeReservationSalle }
     * 
     * 
     */
    public List<DemandeReservationSalle> getDemandeReservations() {
        if (demandeReservations == null) {
            demandeReservations = new ArrayList<DemandeReservationSalle>();
        }
        return this.demandeReservations;
    }

}
