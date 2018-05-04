package fr.unice.polytech.isa.polyevent.demandeevenement.bean;

import fr.unice.polytech.isa.polyevent.entities.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Token;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local
public interface CreerEvenement
{
    String demanderCreationEvenement(Token token, String nom,
                                   Date dateDebut, Date dateFin,
                                   List<DemandeReservationSalle> demandeReservationSalles,
                                   List<DemandePrestataire> demandePrestataires);
}
