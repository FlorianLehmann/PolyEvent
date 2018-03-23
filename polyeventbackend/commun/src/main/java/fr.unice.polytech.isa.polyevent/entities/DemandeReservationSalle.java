package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;

public class DemandeReservationSalle {

    private Date dateDebut;
    private Date dateFin;
    private TypeSalle typeSalle;

    public DemandeReservationSalle() {
    }

    public DemandeReservationSalle(Date dateDebut, Date dateFin, TypeSalle typeSalle) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeSalle = typeSalle;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

}
