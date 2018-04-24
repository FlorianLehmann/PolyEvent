package fr.unice.polytech.isa.polyevent.api;


import fr.unice.polytech.isa.polyevent.stubs.DemandeEvenementService;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.PayerEvenement;
import fr.unice.polytech.isa.polyevent.stubs.PayerService;


import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PolyEventAPI
{
    private final String host;
    private final String port;
    public final DemanderEvenement demandeEvenement;
    public final PayerEvenement payerEvenement;

    public PolyEventAPI(String host, String port)
    {
        this.host = host;
        this.port = port;
        this.demandeEvenement = initDemanderEvenement();
        this.payerEvenement = initPayerEvenement();
    }

    private PayerEvenement initPayerEvenement()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/PayerEvenement.wsdl");
        PayerService factory = new PayerService(wsdlLocation);
        PayerEvenement payEvent = factory.getPayerPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/PayerEvenementWS", host, port);
        ((BindingProvider) payEvent).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return payEvent;
    }

    private DemanderEvenement initDemanderEvenement()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/DemandeEvenement.wsdl");
        DemandeEvenementService factory = new DemandeEvenementService(wsdlLocation);
        DemanderEvenement submitEvent = factory.getDemandeEvenementPort();
        String address = String.format("http://%s:%s/polyeventbackend-war/webservices/DemandeEvenementWS", host, port);
        ((BindingProvider) submitEvent).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return submitEvent;
    }
}
