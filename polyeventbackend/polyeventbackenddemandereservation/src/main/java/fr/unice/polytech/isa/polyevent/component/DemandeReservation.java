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
    public void accepterReservation(Reservation reservation, Salle salle) {
        for (Reservation r: memoire.getReservations()) {
            if(r.equals(reservation)){
                boolean succes = hyperPlanningAPI.reserverSalle(r, salle);
                if (succes) {
                    r.setSalle(salle);
                    r.setStatut(Statut.VALIDE);
                    salle.getReservations().add(r);
                    if (reservation.getEvenement().getReservations() == null){
                        reservation.getEvenement().setReservations(new ArrayList<>());
                    }
                    reservation.getEvenement().getReservations().add(r);

                    return;
                }
            }
        }
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
        for (Salle salle: memoire.getSalles() ){
            boolean dispnible = true;
            if(salle.getTypeSalle().equals(reservation.getTypeSalle())){
                for (Reservation r: salle.getReservations()) {
                        if((r.getDateDebut().compareTo(reservation.getDateDebut())<=0 && r.getDateFin().compareTo(reservation.getDateFin())>=0)
                          || (r.getDateDebut().compareTo(reservation.getDateDebut())>=0 && r.getDateDebut().compareTo(reservation.getDateFin())<=0)
                          || (r.getDateFin().compareTo(reservation.getDateDebut())>=0 && r.getDateFin().compareTo(reservation.getDateFin())<=0)  ){
                            dispnible = false;
                        }
                    }
            }

            if(!salle.getTypeSalle().equals(reservation.getTypeSalle())) {
                dispnible = false;
            }

            if(dispnible){
                accepterReservation(reservation, salle);
                return;
            }

        }
        refuserReservation(reservation, "pas de salle disponible");
    }

    public void setHyperPlanningAPI(HyperPlanningAPI hyperPlanningAPI) {
        this.hyperPlanningAPI = hyperPlanningAPI;
    }
}
