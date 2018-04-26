package fr.unice.polytech.isa.polyevent.session;


import fr.unice.polytech.isa.polyevent.entities.Token;
import fr.unice.polytech.isa.polyevent.entities.interceptors.VerifierToken;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Date;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/payerEvenement")
public interface PayerEvenement
{
    @WebMethod
    @Interceptors({VerifierToken.class})
    @WebResult(name = "status")
    String payerEvenement(@WebParam(name = "token") Token token,
                          @WebParam(name = "nom") String nom,
                          @WebParam(name = "date_debut") Date dateDebut,
                          @WebParam(name = "date_fin") Date dateFin,
                          @WebParam(name = "creditCard") String creditCard);
}
