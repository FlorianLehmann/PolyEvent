package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;

public class Prestation {

    private Date dateDebut;
    private Date dateFin;
    private Prestataire prestataire;
    private Evenement evenement;

    public Prestation(){};

    public Prestation(Date dateDebut, Date dateFin, Prestataire prestataire, Evenement evenement) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prestataire = prestataire;
        this.evenement = evenement;
    }
}
