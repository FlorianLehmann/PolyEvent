package fr.unice.polytech.isa.polyevent.components;

import fr.unice.polytech.isa.polyevent.demanderReservation;
import fr.unice.polytech.isa.polyevent.validerReservation;

import javax.ejb.Stateless;

@Stateless
public class demandeReservation implements demanderReservation, validerReservation {

    public boolean accepterReservation(){
        return true;
    }

}
