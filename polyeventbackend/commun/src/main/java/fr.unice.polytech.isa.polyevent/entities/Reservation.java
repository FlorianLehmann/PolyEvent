package fr.unice.polytech.isa.polyevent.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Expose
    private Date dateDebut;

    @NotNull
    @Expose
    private Date dateFin;

    @Enumerated(EnumType.STRING)
    @Expose
    private TypeSalle typeSalle;

    @Expose
    private Salle salle;

    @NotNull
    private Evenement evenement;

    @NotNull
    @Expose
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Reservation() {
    }

    public Reservation(Date dateDebut, Date dateFin, TypeSalle typeSalle, Salle salle, Evenement evenement, Statut statut ){
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeSalle = typeSalle;
        this.salle = salle;
        this.evenement = evenement;
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(dateDebut, that.dateDebut) &&
                Objects.equals(dateFin, that.dateFin) &&
                typeSalle == that.typeSalle &&
                Objects.equals(salle, that.salle) &&
                Objects.equals(evenement, that.evenement) &&
                statut == that.statut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateDebut, dateFin, typeSalle, salle, evenement.getNom(), statut);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Statut getStatut() {
        return statut;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Salle getSalle() { return salle; }

    @XmlTransient
    public Evenement getEvenement() {
        return evenement;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
