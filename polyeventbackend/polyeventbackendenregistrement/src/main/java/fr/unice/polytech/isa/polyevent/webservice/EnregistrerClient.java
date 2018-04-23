package fr.unice.polytech.isa.polyevent.webservice;


import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientDejaCreeException;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/enregistrerClient")
public interface EnregistrerClient {

    @WebMethod
    void enregistrerClient(@WebParam(name = "mail")String mail) throws ClientDejaCreeException;
}
