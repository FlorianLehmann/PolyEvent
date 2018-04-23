package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.ObtenirProfilOrganisateur;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
@Stateless(name = "evenementOrganisateurWS")
public class EvenementOrganisateur implements ObtenirEvenementOrganisateur {

    @EJB
    private ObtenirProfilOrganisateur obtenirProfilOrganisateur;


    @Override
    public List<Evenement> obtenirEvenementOrganisateur(Organisateur organisateur) {
        return obtenirProfilOrganisateur.obtenirEvenementOrganisateur(organisateur);
    }
}
