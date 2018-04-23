package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;


@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/obtenirEvenementOrganisateur")
public interface ObtenirEvenementOrganisateur {

    @WebMethod
    @WebResult(name = "evenements")
     List<Evenement> obtenirEvenementOrganisateur (@WebParam(name = "organisateur")Organisateur organisateur);


}
