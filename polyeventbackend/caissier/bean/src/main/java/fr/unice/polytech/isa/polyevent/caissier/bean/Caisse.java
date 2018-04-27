package fr.unice.polytech.isa.polyevent.caissier.bean;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface Caisse
{
    StatusCaisse payer(Organisateur organisateur, String nom, Date dateDebut, Date dateFin, String creditCard);
}
