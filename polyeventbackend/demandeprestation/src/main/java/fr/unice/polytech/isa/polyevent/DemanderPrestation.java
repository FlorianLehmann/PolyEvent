package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.TypeService;
import fr.unice.polytech.isa.polyevent.entities.interceptors.VerifierPlageHorraire;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.interceptor.Interceptors;

@Local
public interface DemanderPrestation {
    @Interceptors({VerifierPlageHorraire.class})
    boolean ajouterService(Evenement evenement, List<DemandePrestataire> demandePrestataire);
}
