package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.ChercherEvenement;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.exceptions.EvenementInconnuException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/obtenirEvenement")
@Stateless(name = "trouverEvenementWS")
public class TrouverEvenement implements ObtenirEvenement{

    @EJB
    private ChercherEvenement chercherEvenement;

    @Override
    public Evenement obtenirEvenement(String nom, Date dateDebut, Date dateFin, Organisateur organisateur) throws EvenementInconnuException{
        Optional<Evenement> evenementOptional = chercherEvenement.chercherEvenement(nom, dateDebut, dateFin, organisateur);
        if(evenementOptional.isPresent()){
            return evenementOptional.get();
        }
        else {
            throw new EvenementInconnuException(nom, dateDebut, dateFin, organisateur);
        }
    }
}
