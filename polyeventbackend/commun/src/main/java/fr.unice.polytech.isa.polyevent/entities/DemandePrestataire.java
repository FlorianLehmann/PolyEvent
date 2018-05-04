package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;

public class DemandePrestataire implements PlageHorraire
{
    private TypeService typeService;
    private Date dateDebut;
    private Date dateFin;

    public DemandePrestataire(){}

    public DemandePrestataire(TypeService typeService, Date dateDebut, Date dateFin) {
        this.typeService = typeService;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void setTypeService(TypeService typeService)
    {
        this.typeService = typeService;
    }

    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin)
    {
        this.dateFin = dateFin;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
}
