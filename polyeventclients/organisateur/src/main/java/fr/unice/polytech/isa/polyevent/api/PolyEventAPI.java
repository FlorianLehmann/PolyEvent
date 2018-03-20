package fr.unice.polytech.isa.polyevent.api;


import fr.unice.polytech.isa.polyevent.stubs.DemandeEvenementService;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;

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
        DemandeEvenementService factory = new DemandeEvenementService();
        return factory.getDemandeEvenementPort();
    }
}
