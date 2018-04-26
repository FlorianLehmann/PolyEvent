package fr.unice.polytech.isa.polyevent.api;


import fr.unice.polytech.isa.polyevent.stubs.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PolyEventAPI
{
    public final DemanderEvenement demandeEvenement;
    public final PayerEvenement payerEvenement;
    public final ConnecterClient connecterClient;
    public final EnregistrerClient enregistrerClient;
    public final ObtenirEvenementOrganisateur obtenirEvenementOrganisateur;
    private final String host;
    private final String port;

    public PolyEventAPI(String host, String port)
    {
        this.host = host;
        this.port = port;
        this.demandeEvenement = initDemanderEvenement();
        this.payerEvenement = initPayerEvenement();
        this.connecterClient = initConnecterClient();
        this.enregistrerClient = initEnregistrerClient();
        this.obtenirEvenementOrganisateur = initListEvenements();
    }

    private PayerEvenement initPayerEvenement()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/PayerEvenementWS.wsdl");
        PayerService factory = new PayerService(wsdlLocation);
        PayerEvenement payEvent = factory.getPayerPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/PayerEvenementWS", host, port);
        ((BindingProvider) payEvent).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return payEvent;
    }

    private ObtenirEvenementOrganisateur initListEvenements()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/evenementOrganisateurWS.wsdl");
        EvenementOrganisateurService factory = new EvenementOrganisateurService(wsdlLocation);
        ObtenirEvenementOrganisateur evenementOrganisateur = factory.getEvenementOrganisateurPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/evenementOrganisateurWS", host, port);
        ((BindingProvider) evenementOrganisateur).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return evenementOrganisateur;
    }

    private EnregistrerClient initEnregistrerClient()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/enregistrementClientWS.wsdl");
        EnregistrementClientService factory = new EnregistrementClientService(wsdlLocation);
        EnregistrerClient connexionClientPort = factory.getEnregistrementClientPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/enregistrementClientWS", host, port);
        ((BindingProvider) connexionClientPort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return connexionClientPort;
    }

    private ConnecterClient initConnecterClient()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/connexionClientWS.wsdl");
        ConnexionClientService factory = new ConnexionClientService(wsdlLocation);
        ConnecterClient connexionClientPort = factory.getConnexionClientPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/connexionClientWS", host, port);
        ((BindingProvider) connexionClientPort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return connexionClientPort;
    }

    private DemanderEvenement initDemanderEvenement()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/DemandeEvenementWS.wsdl");
        DemandeEvenementService factory = new DemandeEvenementService(wsdlLocation);
        DemanderEvenement submitEvent = factory.getDemandeEvenementPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/DemandeEvenementWS", host, port);
        ((BindingProvider) submitEvent).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return submitEvent;
    }
}
