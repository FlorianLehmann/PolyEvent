package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Reservation;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DemanderReservation {

    void demanderReservationSalle(Evenement evenement, List<DemandeReservationSalle> demandeReservationSalles);

    void setHyperPlanningAPI(HyperPlanningAPI mocked);
}
