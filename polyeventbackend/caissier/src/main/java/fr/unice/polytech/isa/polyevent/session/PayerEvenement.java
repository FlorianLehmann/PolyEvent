package fr.unice.polytech.isa.polyevent.session;


import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Date;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/payerEvenement")
public interface PayerEvenement
{
    @WebMethod
    @WebResult(name = "status")
    String payerEvenement(@WebParam(name = "organisateur") Organisateur organisateur,
                          @WebParam(name = "nom") String nom,
                          @WebParam(name = "date_debut") Date dateDebut,
                          @WebParam(name = "date_fin") Date dateFin,
                          @WebParam(name = "creditCard") String creditCard);
}
