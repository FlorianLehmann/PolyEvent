package fr.unice.polytech.isa.polyevent.webservice;


import fr.unice.polytech.isa.polyevent.entities.Token;
import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientPasEnregistreException;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/connecterClient")
public interface ConnecterClient {

    @WebMethod
    @WebResult(name="token")
    Token connexion(@WebParam(name = "mail")String mail) throws ClientPasEnregistreException;
}
