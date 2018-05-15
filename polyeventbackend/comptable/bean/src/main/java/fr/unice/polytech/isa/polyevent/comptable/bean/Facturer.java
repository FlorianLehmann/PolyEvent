package fr.unice.polytech.isa.polyevent.comptable.bean;

import fr.unice.polytech.isa.polyevent.entities.Evenement;

import javax.ejb.Local;

@Local
public interface Facturer
{
    StatusPayement facturer(Evenement evenement);
}
