package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.entities.interceptors.VerifierToken;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
public interface DemanderEvenement {



    @WebMethod
    @Interceptors({VerifierToken.class})
    void demanderCreationEvenement(@WebParam(name = "token") Token token,
                                   @WebParam(name = "nom") String nom,
                                   @WebParam(name = "date_debut") Date dateDebut,
                                   @WebParam(name = "date_fin") Date dateFin,
                                   @WebParam(name = "demande_reservations") List<DemandeReservationSalle> demandeReservationSalles,
                                   @WebParam(name = "demande_prestataire") List<DemandePrestataire> demandePrestataires);

    @WebMethod
    @Interceptors({VerifierToken.class})
    @WebResult(name = "evenements")
    List<Evenement> getEvenements(@WebParam(name = "token") Token token);


}
