package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.TypeService;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DemanderPrestation {
    boolean ajouterService(Evenement evenement, List<DemandePrestataire> demandePrestataire);

}
