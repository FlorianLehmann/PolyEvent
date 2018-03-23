package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import java.util.Date;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
public interface DemanderEvenement {

    @WebMethod
    void demanderCreationEvenement(@WebParam(name = "organisateur") Organisateur organisateur,
                                   @WebParam(name = "nom") String nom,
                                   @WebParam(name = "date_debut") Date dateDebut,
                                   @WebParam(name = "date_fin") Date dateFin,
                                   @WebParam(name = "demande_reservations") List<DemandeReservationSalle> demandeReservationSalles);

    @WebMethod
    @WebResult(name = "evenements")
    List<Evenement> getEvenements(@WebParam(name = "organisateur") Organisateur organisateur);
}
