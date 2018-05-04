package fr.unice.polytech.isa.polyevent.entities.exceptions;

import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import java.util.Date;

public class EvenementInconnuException extends Exception {

    private Organisateur bob;
    private Date debut;
    private Date fin;
    private String nom;

    public EvenementInconnuException(String nom,  Date debut, Date fin, Organisateur bob ) {
        this.bob = bob;
        this.debut = debut;
        this.fin = fin;
        this.nom = nom;
    }

   public EvenementInconnuException(){}

    public Organisateur getBob() {
        return bob;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public String getNom() {
        return nom;
    }

    public void setBob(Organisateur bob) {
        this.bob = bob;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
