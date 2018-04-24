package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.Salle;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ValiderReservation {

    boolean accepterReservation(Reservation reservation, Salle salle);
    void refuserReservation(Reservation reservation, String raison);
    List<Reservation> getReservations();

}
