package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;

public class DemandeReservationSalle {

    private Date dateDebut;
    private Date dateFin;
    private TypeSalle typeSalle;
    private String nom;

    public DemandeReservationSalle(Date dateDebut, Date dateFin, TypeSalle typeSalle, String nom) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeSalle = typeSalle;
        this.nom = nom;
    }

}
