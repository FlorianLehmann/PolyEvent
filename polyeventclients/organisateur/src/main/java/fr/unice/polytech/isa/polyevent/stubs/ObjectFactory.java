
package fr.unice.polytech.isa.polyevent.stubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.polytech.isa.polyevent.stubs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DemanderCreationEvenement_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", "demanderCreationEvenement");
    private final static QName _DemanderCreationEvenementResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", "demanderCreationEvenementResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.polytech.isa.polyevent.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DemanderCreationEvenement }
     * 
     */
    public DemanderCreationEvenement createDemanderCreationEvenement() {
        return new DemanderCreationEvenement();
    }

    /**
     * Create an instance of {@link DemanderCreationEvenementResponse }
     * 
     */
    public DemanderCreationEvenementResponse createDemanderCreationEvenementResponse() {
        return new DemanderCreationEvenementResponse();
    }

    /**
     * Create an instance of {@link Organisateur }
     * 
     */
    public Organisateur createOrganisateur() {
        return new Organisateur();
    }

    /**
     * Create an instance of {@link Mail }
     * 
     */
    public Mail createMail() {
        return new Mail();
    }

    /**
     * Create an instance of {@link DemandeReservationSalle }
     * 
     */
    public DemandeReservationSalle createDemandeReservationSalle() {
        return new DemandeReservationSalle();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemanderCreationEvenement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", name = "demanderCreationEvenement")
    public JAXBElement<DemanderCreationEvenement> createDemanderCreationEvenement(DemanderCreationEvenement value) {
        return new JAXBElement<DemanderCreationEvenement>(_DemanderCreationEvenement_QNAME, DemanderCreationEvenement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemanderCreationEvenementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", name = "demanderCreationEvenementResponse")
    public JAXBElement<DemanderCreationEvenementResponse> createDemanderCreationEvenementResponse(DemanderCreationEvenementResponse value) {
        return new JAXBElement<DemanderCreationEvenementResponse>(_DemanderCreationEvenementResponse_QNAME, DemanderCreationEvenementResponse.class, null, value);
    }

}
