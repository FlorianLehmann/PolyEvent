package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.StatusHistorique;
import fr.unice.polytech.isa.polyevent.utils.Database;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
@Stateless(name = "DemandeEvenementWS")
public class DemandeEvenement implements DemanderEvenement {

    @EJB private DemanderReservation demandeReservation;
    @EJB private Database memoire;

    @Override
    public void demanderCreationEvenement(Organisateur organisateur, String nom, Date dateDebut, Date dateFin, List<DemandeReservationSalle> demandeReservationSalles) {
        Evenement evenement = new Evenement(nom, dateDebut, dateFin, organisateur, null, new StatusHistorique());
        organisateur.getEvenements().add(evenement);
        if (demandeReservationSalles == null)
            demandeReservationSalles = new ArrayList<>();

        demandeReservation.demanderReservationSalle(evenement, demandeReservationSalles);

        memoire.getEvenements().add(evenement);

    }

    @Override
    public List<Evenement> getEvenements(Organisateur organisateur) {
        List<Evenement> evenements = new ArrayList<>();
        for (Evenement evenement :
                memoire.getEvenements()) {
            if (evenement.getOrganisateur().equals(organisateur)) {
                evenements.add(evenement);
            }
        }
        return evenements;
    }
}
