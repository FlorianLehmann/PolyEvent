package fr.unice.polytech.isa.polyevent.webservice;


import fr.unice.polytech.isa.polyevent.TrouverOrganisateur;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.Token;
import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientPasEnregistreException;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/connecterClient")
@Stateless(name = "connexionClientWS")
public class ConnexionClient implements ConnecterClient{

    @EJB private TrouverOrganisateur trouverOrganisateur;

    @Override
    public Token connexion(String mail) throws ClientPasEnregistreException {
        Optional<Organisateur> optionalOrganisateur = trouverOrganisateur.connexion(mail);
        if(optionalOrganisateur.isPresent()){
            Token token = new Token(optionalOrganisateur.get(), new Date(2020,1, 1));
            return token;
        }
        else {
            throw new ClientPasEnregistreException(mail);
        }

    }
}
