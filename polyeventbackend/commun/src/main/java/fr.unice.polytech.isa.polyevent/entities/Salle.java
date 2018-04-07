package fr.unice.polytech.isa.polyevent.entities;

public class Salle {
    private String nom;

    public Salle() {

    }

    public Salle(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }
}
