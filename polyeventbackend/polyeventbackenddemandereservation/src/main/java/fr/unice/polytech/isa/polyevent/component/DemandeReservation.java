package fr.unice.polytech.isa.polyevent.component;

import fr.unice.polytech.isa.polyevent.demanderReservation;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.validerReservation;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DemandeReservation implements demanderReservation, validerReservation {

    public boolean accepterReservation(){
        return true;
    }

    @Override
    public void demanderReservationSalle(Evenement evenement, List<DemandeReservationSalle> demandeReservationSalles) {

    }
}
