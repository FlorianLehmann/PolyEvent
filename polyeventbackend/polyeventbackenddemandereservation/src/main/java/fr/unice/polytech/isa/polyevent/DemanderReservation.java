package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Evenement;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DemanderReservation {

    void demanderReservationSalle(Evenement evenement, List<DemandeReservationSalle> demandeReservationSalles);
}
