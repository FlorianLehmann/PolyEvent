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


    public Evenement() {

    }


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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    public StatusHistorique getStatusHistorique() {
        return statusHistorique;
    }

    public void setStatusHistorique(StatusHistorique statusHistorique) {
        this.statusHistorique = statusHistorique;
    }
}
