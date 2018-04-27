package fr.unice.polytech.isa.polyevent.comptable;

import fr.unice.polytech.isa.polyevent.entities.Evenement;

import javax.ejb.Stateless;

@Stateless
public class Comptable implements Facturer
{
    @Override
    public StatusPayement facturer(Evenement evenement)
    {
        return StatusPayement.PAYEMENT_EFFECTUER;
    }
}
