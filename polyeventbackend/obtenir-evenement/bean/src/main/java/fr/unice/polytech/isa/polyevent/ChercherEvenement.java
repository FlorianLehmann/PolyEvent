package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Local;
import java.util.Date;
import java.util.Optional;

@Local
public interface ChercherEvenement {

    Optional<Evenement> chercherEvenement(String nom, Date dateDebut, Date dateFin, Organisateur organisateur);
}
