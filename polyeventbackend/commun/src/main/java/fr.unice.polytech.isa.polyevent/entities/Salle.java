package fr.unice.polytech.isa.polyevent.entities;

import java.util.List;

public class Salle {
    private TypeSalle typeSalle;
    private List<Reservation> reservations;

    public Salle(TypeSalle typeSalle, List<Reservation> reservations) {
        this.typeSalle = typeSalle;
        this.reservations = reservations;
    }
}
