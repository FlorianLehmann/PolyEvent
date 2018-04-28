package fr.unice.polytech.isa.polyevent.comptable;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Local;

@Local
public interface EnvoyerFacture {
    void envoyerFacture(Organisateur organisateur, Evenement evenement);
}
