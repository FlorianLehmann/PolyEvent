package fr.unice.polytech.isa.polyevent.entities;

import java.util.Date;

public class Token
{
    private Organisateur organisateur;
    private Date dateDeValidite;

    public Token()
    {
    }

    public Token(Organisateur organisateur, Date dateDeValidite)
    {
        this.organisateur = organisateur;
        this.dateDeValidite = dateDeValidite;
    }

    public Organisateur getOrganisateur()
    {
        return organisateur;
    }

    public Date getDateDeValidite()
    {
        return dateDeValidite;
    }
}
