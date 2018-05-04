package fr.unice.polytech.isa.polyevent.component;


import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.HyperPlanningAPI;
import fr.unice.polytech.isa.polyevent.ValiderReservation;
import fr.unice.polytech.isa.polyevent.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class DemandeReservation implements DemanderReservation, ValiderReservation {


    @PersistenceContext
    private EntityManager entityManager;

    private HyperPlanningAPI hyperPlanningAPI = new HyperPlanningAPI();


    @Override
    public void demanderReservationSalle(Evenement evenement, List<DemandeReservationSalle> demandeReservationSalles) {
        evenement = entityManager.merge(evenement);
        for (DemandeReservationSalle demandeReservationSalle: demandeReservationSalles) {
            Reservation reservation = new Reservation(demandeReservationSalle.getDateDebut(), demandeReservationSalle.getDateFin(),
                    demandeReservationSalle.getTypeSalle(), null, evenement, Statut.EN_ATTENTE_DE_VALIDATION);
            //persistence
            entityManager.persist(reservation);
            validationAutomatique(reservation);
            evenement.setStatut(Statut.En_ATTENTE_DE_PAIEMENT);
        }
    }

    @Override
    public boolean accepterReservation(Reservation reservation, Salle salle) {
        reservation = entityManager.merge(reservation);
        String reponse = hyperPlanningAPI.reserverSalle(reservation, salle);

        if (reponse.equals("Succès")) {
            reservation.setSalle(salle);
            reservation.setStatut(Statut.VALIDE);
            if (reservation.getEvenement().getReservations() == null){
                reservation.getEvenement().setReservations(new ArrayList<>());
            }
            reservation.getEvenement().getReservations().add(reservation);
            return true;
        }

        return false;

    }

    @Override
    public void refuserReservation(Reservation reservation, String raison) {
        reservation = entityManager.merge(reservation);
        reservation.setStatut(Statut.REFUSE);
        if (reservation.getEvenement().getReservations() == null){
            reservation.getEvenement().setReservations(new ArrayList<>());
        }
        reservation.getEvenement().getReservations().add(reservation);
    }

    private void validationAutomatique(Reservation reservation){
        reservation = entityManager.merge(reservation);

        List<Reservation> reservations = new LinkedList<>();
        reservations.add(reservation);
        List<String> strings = new LinkedList<>();
        try {
            strings =  hyperPlanningAPI.DemandeSallesDisponible(reservations);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            refuserReservation(reservation, "pas de salle disponible");
        }
        if(!strings.isEmpty()){
            accepterReservation(reservation, new Salle(strings.get(0)));
            return;
        }
        refuserReservation(reservation, "pas de salle disponible");
    }

    public void setHyperPlanningAPI(HyperPlanningAPI hyperPlanningAPI) {
        this.hyperPlanningAPI = hyperPlanningAPI;
    }

    @Override
    public List<Reservation> getReservations() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
        Root<Reservation> root =  criteria.from(Reservation.class);

        criteria.select(root);
        TypedQuery<Reservation> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }
}
