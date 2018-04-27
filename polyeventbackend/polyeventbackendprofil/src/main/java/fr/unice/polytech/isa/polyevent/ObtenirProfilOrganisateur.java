package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Local
public interface ObtenirProfilOrganisateur {

    List<Evenement> obtenirEvenementsOrganisateur(Organisateur organisateur);
    Optional<Evenement> obtenirEvenementOrganisateur (Organisateur organisateur, String nom, Date dateDebut, Date dateFin);

}
