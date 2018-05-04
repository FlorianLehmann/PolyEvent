package fr.unice.polytech.isa.polyevent.demandeevenement.bean;

import fr.unice.polytech.isa.polyevent.ChercherEvenement;
import fr.unice.polytech.isa.polyevent.DemanderPrestation;
import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.entities.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class CreateurEvenement implements CreerEvenement
{
    @EJB
    private DemanderReservation demandeReservation;
    @EJB
    private DemanderPrestation demanderPrestation;
    @EJB
    private ChercherEvenement chercherEvenement;

    @PersistenceContext
    private EntityManager entityManager;

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
        Organisateur organisateur = token.getOrganisateur();
        if(chercherEvenement.chercherEvenement(nom, dateDebut, dateFin, organisateur).isPresent()) {
            return "Evenement déjà créé";
        }

        Evenement evenement = new Evenement(nom, dateDebut, dateFin, organisateur, new ArrayList<>(), Statut.EN_ATTENTE_DE_VALIDATION);
        entityManager.persist(evenement);
        organisateur.getEvenements().add(evenement);
        demandeReservation.demanderReservationSalle(evenement, demandeReservationSalles);
        demanderPrestation.ajouterService(evenement, demandePrestataires);
        return "Succés";
    }
}
