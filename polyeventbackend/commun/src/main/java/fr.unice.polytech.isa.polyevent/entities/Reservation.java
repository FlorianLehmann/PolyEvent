package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;

public class Reservation {

    private Date dateDebut;
    private Date dateFin;
    private TypeSalle typeSalle;
    private Salle salle;
    private Evenement evenement;
    private Statut statut;

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

        if (dateDebut != null ? !dateDebut.equals(that.dateDebut) : that.dateDebut != null) return false;
        if (dateFin != null ? !dateFin.equals(that.dateFin) : that.dateFin != null) return false;
        if (typeSalle != that.typeSalle) return false;
        if (salle != null ? !salle.equals(that.salle) : that.salle != null) return false;
        if (evenement != null ? !evenement.equals(that.evenement) : that.evenement != null) return false;
        return statut == that.statut;
    }

    @Override
    public int hashCode() {
        int result = dateDebut != null ? dateDebut.hashCode() : 0;
        result = 31 * result + (dateFin != null ? dateFin.hashCode() : 0);
        result = 31 * result + (typeSalle != null ? typeSalle.hashCode() : 0);
        result = 31 * result + (salle != null ? salle.hashCode() : 0);
        result = 31 * result + (evenement != null ? evenement.hashCode() : 0);
        result = 31 * result + (statut != null ? statut.hashCode() : 0);
        return result;
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

    public Evenement getEvenement() {
        return evenement;
    }
}
