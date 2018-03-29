package fr.unice.polytech.isa.polyevent.entities;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

public class Salle {
    private TypeSalle typeSalle;
    private List<Reservation> reservations;
    private String nom;

    public Salle() {

    }

    public Salle(TypeSalle typeSalle, List<Reservation> reservations, String nom) {
        this.typeSalle = typeSalle;
        this.reservations = reservations;
        this.nom = nom;
    }

    @XmlTransient
    public List<Reservation> getReservations() {
        return reservations;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public String getNom() {
        return nom;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
