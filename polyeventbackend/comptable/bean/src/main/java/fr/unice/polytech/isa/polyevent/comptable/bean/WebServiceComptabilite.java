package fr.unice.polytech.isa.polyevent.comptable.bean;

import fr.unice.polytech.isa.polyevent.entities.DemandeEnvoieFacture;

import javax.ejb.Local;

@Local
public interface WebServiceComptabilite {
    void DemanderFactureServeurComptabilite(DemandeEnvoieFacture demandeEnvoieFacture);
}
