package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.TypeService;

import java.util.Date;
import javax.ejb.Local;

@Local
public interface DemanderPrestation {
    boolean ajouterService(Evenement evenement, TypeService typeService, Date dateDebut, Date dateFin);

}
