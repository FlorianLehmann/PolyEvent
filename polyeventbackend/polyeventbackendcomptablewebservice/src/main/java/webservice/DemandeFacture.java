package webservice;

import fr.unice.polytech.isa.polyevent.ProfilClient;
import fr.unice.polytech.isa.polyevent.comptable.Comptable;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Token;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demanderFacture")
@Stateless(name = "demandeEvenementWS")
public class DemandeFacture implements DemanderFacture
{
    @EJB
    private Comptable comptable;
    @EJB
    private ProfilClient profilClient;

    @Override
    public String obtenirEvenementOrganisateur(Token token, String nom, Date dateDebut, Date dateFin) {
        Optional<Evenement> evenement = profilClient.obtenirEvenementOrganisateur(token.getOrganisateur(),nom,dateDebut,dateFin);
        if (evenement.isPresent()) {
            comptable.envoyerFacture(token.getOrganisateur(), evenement.get());
            return "OK";
        }
        else
            return "Evenement Inconnu";
    }
}
