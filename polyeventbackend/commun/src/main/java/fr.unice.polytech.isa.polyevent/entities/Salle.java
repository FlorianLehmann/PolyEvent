package fr.unice.polytech.isa.polyevent.entities;

import java.util.List;

public class Salle {
    private TypeSalle typeSalle;
    private List<Reservation> reservations;
    private String nom;

    public Salle(TypeSalle typeSalle, List<Reservation> reservations, String nom) {
        this.typeSalle = typeSalle;
        this.reservations = reservations;
        this.nom = nom;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public String getNom() {
        return nom;
    }

}
