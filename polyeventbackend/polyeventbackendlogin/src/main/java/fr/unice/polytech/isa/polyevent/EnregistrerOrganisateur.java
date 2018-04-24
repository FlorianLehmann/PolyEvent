package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientDejaCreeException;

import javax.ejb.Local;

@Local
public interface EnregistrerOrganisateur {

    void enregistrer(String mail) throws ClientDejaCreeException;


}
