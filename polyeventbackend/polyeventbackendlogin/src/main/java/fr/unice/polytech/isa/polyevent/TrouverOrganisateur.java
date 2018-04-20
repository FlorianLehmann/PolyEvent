package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface TrouverOrganisateur {

    Optional<Organisateur> connexion(String mail);
}
