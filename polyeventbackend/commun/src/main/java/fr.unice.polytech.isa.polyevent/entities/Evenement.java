package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;
import java.util.List;

public class Evenement {

    private String nom;
    private Date debut;
    private Date fin;
    private Organisateur organisateur;
    private List<Reservation> reservations;
    private StatusHistorique statusHistorique;

    public Evenement(String nom, Date debut, Date fin, Organisateur organisateur, List<Reservation> reservations, StatusHistorique statusHistorique) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.organisateur = organisateur;
        this.reservations = reservations;
        this.statusHistorique = statusHistorique;
    }

    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public String getNom() {
        return nom;
    }
}
