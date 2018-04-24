package fr.unice.polytech.isa.polyevent.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String nom;

    @NotNull
    private Date debut;

    @NotNull
    private Date fin;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Organisateur organisateur;

    @ElementCollection
    private List<Reservation> reservations;

    @NotNull
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
        this.organisateur.getEvenements().add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return  Objects.equals(nom, evenement.nom) &&
                Objects.equals(debut, evenement.debut) &&
                Objects.equals(fin, evenement.fin) &&
                Objects.equals(organisateur, evenement.organisateur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nom, debut, fin, organisateur);
    }
}
