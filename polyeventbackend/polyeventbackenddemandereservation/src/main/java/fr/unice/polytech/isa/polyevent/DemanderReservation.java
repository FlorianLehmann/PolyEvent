package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.interceptors.VerifierPlageHorraire;

import javax.ejb.Local;
import javax.interceptor.Interceptors;
import java.util.List;

@Local
public interface DemanderReservation {

    @Interceptors({VerifierPlageHorraire.class})
    void demanderReservationSalle(Evenement evenement, List<DemandeReservationSalle> demandeReservationSalles);

    void setHyperPlanningAPI(HyperPlanningAPI mocked);
}
