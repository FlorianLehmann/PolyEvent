package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.DemanderPrestation;
import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.entities.*;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement")
@Stateless(name = "DemandeEvenementWS")
public class DemandeEvenement implements DemanderEvenement {

    @EJB private DemanderReservation demandeReservation;
    @EJB private DemanderPrestation demanderPrestation;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void demanderCreationEvenement(Organisateur organisateur, String nom, Date dateDebut, Date dateFin, List<DemandeReservationSalle> demandeReservationSalles, List<DemandePrestataire> demandePrestataires) {
        Evenement evenement = new Evenement(nom, dateDebut, dateFin, organisateur, new ArrayList<>(), new StatusHistorique());
        entityManager.persist(evenement);
        organisateur.getEvenements().add(evenement);
        if (demandeReservationSalles == null)
            demandeReservationSalles = new ArrayList<>();

        demandeReservation.demanderReservationSalle(evenement, demandeReservationSalles);

        if(demandePrestataires ==null){
            demandePrestataires = new ArrayList<>();
        }

        demanderPrestation.ajouterService(evenement, demandePrestataires);



    }

    @Override
    public List<Evenement> getEvenements(Organisateur organisateur) {
        return organisateur.getEvenements();
    }
}
