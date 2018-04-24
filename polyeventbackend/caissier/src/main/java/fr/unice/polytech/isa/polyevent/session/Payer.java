package fr.unice.polytech.isa.polyevent.session;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/payerEvenement")
@Stateless(name = "PayerEvenementWS")
public class Payer implements PayerEvenement
{
    private static final String OK = "OK";

    @Override
    public String payerEvenement(Organisateur organisateur, String nom, Date dateDebut, Date dateFin, String creditCard)
    {
        return OK;
    }
}
