package webservice;

import fr.unice.polytech.isa.polyevent.ObtenirProfilOrganisateur;
import fr.unice.polytech.isa.polyevent.comptable.EnvoyerFacture;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Token;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demanderFacture")
@Stateless(name = "demandeFactureWS")
public class DemandeFacture implements DemanderFacture
{
    @EJB
    private EnvoyerFacture comptable;
    @EJB
    private ObtenirProfilOrganisateur profilClient;

    @Override
    public String obtenirFacture(Token token, String nom, Date dateDebut, Date dateFin)
    {
        System.out.println(token.getOrganisateur().getMail());
        System.out.println(nom);
        System.out.println(dateDebut);
        System.out.println(dateFin);
        Optional<Evenement> evenement = profilClient.obtenirEvenementOrganisateur(token.getOrganisateur(), nom, dateDebut, dateFin);
        if (evenement.isPresent())
        {
            System.out.println("LLGDknfifnibijvbeuveoicnqijqidqidqidnqdnqidnqsidn\n\n\n\n");
            comptable.envoyerFacture(token.getOrganisateur(), evenement.get());
            return "OK";
        }
        else {
            System.out.println("LLaa\n\n\n\n");
            return "Evenement Inconnu";
        }

    }
}
