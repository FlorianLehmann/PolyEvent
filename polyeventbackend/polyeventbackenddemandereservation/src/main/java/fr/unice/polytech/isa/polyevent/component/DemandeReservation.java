package fr.unice.polytech.isa.polyevent.component;



import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.HyperPlanningAPI;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.utils.Database;
import fr.unice.polytech.isa.polyevent.ValiderReservation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class DemandeReservation implements DemanderReservation, ValiderReservation {

    @EJB
    private Database memoire;

    private HyperPlanningAPI hyperPlanningAPI = new HyperPlanningAPI();


    @Override
    public void demanderReservationSalle(Evenement evenement, List<DemandeReservationSalle> demandeReservationSalles) {
        for (DemandeReservationSalle demandeReservationSalle: demandeReservationSalles) {
            Reservation reservation = new Reservation(demandeReservationSalle.getDateDebut(), demandeReservationSalle.getDateFin(),
                    demandeReservationSalle.getTypeSalle(), null, evenement, Statut.EN_ATTENTE_DE_VALIDATION);
            memoire.getReservations().add(reservation);
            validationAutomatique(reservation);
        }
    }

    @Override
    public boolean accepterReservation(Reservation reservation, Salle salle) {
        for (Reservation r: memoire.getReservations()) {
            if(r.equals(reservation)){
                String reponse = hyperPlanningAPI.reserverSalle(r, salle);
                if (reponse.equals("Succès")) {
                    r.setSalle(salle);
                    r.setStatut(Statut.VALIDE);
                    if (reservation.getEvenement().getReservations() == null){
                        reservation.getEvenement().setReservations(new ArrayList<>());
                    }
                    reservation.getEvenement().getReservations().add(r);

                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public void refuserReservation(Reservation reservation, String raison) {
        for (Reservation r: memoire.getReservations()) {
            if(r.equals(reservation)){
                r.setStatut(Statut.REFUSE);
                if (reservation.getEvenement().getReservations() == null){
                    reservation.getEvenement().setReservations(new ArrayList<>());
                }
                reservation.getEvenement().getReservations().add(r);
            }
        }
    }



    private void validationAutomatique(Reservation reservation){
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
}
