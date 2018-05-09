package fr.unice.polytech.isa.polyevent.webservice;


import fr.unice.polytech.isa.polyevent.EnregistrerOrganisateur;
import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientDejaCreeException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/enregistrerClient")
@Stateless(name = "enregistrementClientWS")
public class EnregistrementClient implements EnregistrerClient{

    @EJB
    private EnregistrerOrganisateur enregistrerOrganisateur;

    @Override
    public void enregistrerClient(String mail) throws ClientDejaCreeException{
        enregistrerOrganisateur.enregistrer(mail);
    }
}
