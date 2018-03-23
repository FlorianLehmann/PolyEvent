package fr.unice.polytech.isa.polyevent.api;


import fr.unice.polytech.isa.polyevent.stubs.DemandeEvenementService;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PolyEventAPI
{
    private final String host;
    private final String port;
    public final DemanderEvenement demandeEvenement;

    public PolyEventAPI(String host, String port)
    {
        this.host = host;
        this.port = port;
        this.demandeEvenement = initDemanderEvenement();
    }

    private DemanderEvenement initDemanderEvenement()
    {
        URL wsdlLocation = PolyEventAPI.class.getResource("/DemandeEvenement.wsdl");
        DemandeEvenementService factory = new DemandeEvenementService(wsdlLocation);
        DemanderEvenement submitEvent = factory.getDemandeEvenementPort();
        String address = String.format("%s:%s/polyeventbackend/webservices/DemandeEvenementWS", host, port);
        ((BindingProvider) submitEvent).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return submitEvent;
    }
}
