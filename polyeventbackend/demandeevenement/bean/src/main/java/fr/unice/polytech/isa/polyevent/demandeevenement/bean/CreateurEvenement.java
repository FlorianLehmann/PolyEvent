package fr.unice.polytech.isa.polyevent.demandeevenement.bean;

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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void demanderCreationEvenement(Token token, String nom, Date dateDebut, Date dateFin, List<DemandeReservationSalle> demandeReservationSalles, List<DemandePrestataire> demandePrestataires)
    {
        Organisateur organisateur = token.getOrganisateur();
        Evenement evenement = new Evenement(nom, dateDebut, dateFin, organisateur, new ArrayList<>(), Statut.EN_ATTENTE_DE_VALIDATION);
        entityManager.persist(evenement);
        organisateur.getEvenements().add(evenement);
        if (demandeReservationSalles == null)
        {
            demandeReservationSalles = new ArrayList<>();
        }

        demandeReservation.demanderReservationSalle(evenement, demandeReservationSalles);

        if (demandePrestataires == null)
        {
            demandePrestataires = new ArrayList<>();
        }

        demanderPrestation.ajouterService(evenement, demandePrestataires);
    }
}
