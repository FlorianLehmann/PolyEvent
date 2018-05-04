package fr.unice.polytech.isa.polyevent.demandeevenement.webservice;

import fr.unice.polytech.isa.polyevent.entities.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Token;
import fr.unice.polytech.isa.polyevent.entities.interceptors.VerifierPlageHorraire;
import fr.unice.polytech.isa.polyevent.entities.interceptors.VerifierToken;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
public interface DemanderEvenement
{
    @WebMethod
    @Interceptors({VerifierToken.class, VerifierPlageHorraire.class})
    void demanderCreationEvenement(@WebParam(name = "token") Token token,
                                   @WebParam(name = "nom") String nom,
                                   @WebParam(name = "date_debut") Date dateDebut,
                                   @WebParam(name = "date_fin") Date dateFin,
                                   @WebParam(name = "demande_reservations") List<DemandeReservationSalle> demandeReservationSalles,
                                   @WebParam(name = "demande_prestataire") List<DemandePrestataire> demandePrestataires);
}
