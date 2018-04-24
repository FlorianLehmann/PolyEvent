package fr.unice.polytech.isa.polyevent.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Prestation {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private Date dateDebut;
    @NotNull
    private Date dateFin;
    @NotNull
    private Prestataire prestataire;
    @NotNull
    private Evenement evenement;

    public Prestation(){};

    public Prestation(Date dateDebut, Date dateFin, Prestataire prestataire, Evenement evenement) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prestataire = prestataire;
        this.evenement = evenement;
        this.prestataire.getPrestations().add(this);
    }
}
