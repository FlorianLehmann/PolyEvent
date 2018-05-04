package fr.unice.polytech.isa.polyevent.demandeevenement.webservice;

import fr.unice.polytech.isa.polyevent.demandeevenement.bean.CreateurEvenement;
import fr.unice.polytech.isa.polyevent.entities.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Token;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
@Stateless(name = "DemandeEvenementWS")
public class DemandeEvenement implements DemanderEvenement
{
    @EJB
    private CreateurEvenement createurEvenement;

    @Override
    public String demanderCreationEvenement(Token token, String nom, Date dateDebut, Date dateFin, List<DemandeReservationSalle> demandeReservationSalles, List<DemandePrestataire> demandePrestataires)
    {
        if (demandeReservationSalles == null)
        {
            demandeReservationSalles = new ArrayList<>();
        }
        if (demandePrestataires == null)
        {
            demandePrestataires = new ArrayList<>();
        }
        return createurEvenement.demanderCreationEvenement(token, nom, dateDebut, dateFin, demandeReservationSalles, demandePrestataires);
    }
}
