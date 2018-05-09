package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.exceptions.EvenementInconnuException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Date;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/obtenirEvenement")
public interface ObtenirEvenement {

    @WebMethod
    @WebResult(name = "evenement")
    Evenement obtenirEvenement(@WebParam(name = "nom") String nom,
                               @WebParam(name = "dateDebut") Date dateDebut,
                               @WebParam(name = "dateFin") Date dateFin,
                               @WebParam(name = "organisateur")Organisateur organisateur) throws EvenementInconnuException;
}
