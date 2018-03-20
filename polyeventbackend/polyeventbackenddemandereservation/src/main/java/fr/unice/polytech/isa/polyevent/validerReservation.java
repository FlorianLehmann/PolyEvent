package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.Salle;

import javax.ejb.Local;

@Local
public interface ValiderReservation {

    void accepterReservation(Reservation reservation, Salle salle);
    void refuserReservation(Reservation reservation, String raison);

}
