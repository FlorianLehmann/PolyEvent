package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.utils.Database;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class DemandePrestation implements DemanderPrestation {

    @EJB
    private Database memoire;

    @EJB
    private EnvoyerMail envoyerMail;

    @Override
    public boolean ajouterService(Evenement evenement, List<DemandePrestataire> demandePrestataires) {
        for (DemandePrestataire demandePrestataire: demandePrestataires) {
            for (Prestataire prestataire : memoire.getPrestataires()) {
                if (demandePrestataire.getTypeService().equals(prestataire.getTypeService())) {
                    envoyerMail.envoieMail(prestataire, demandePrestataire.getDateDebut(), demandePrestataire.getDateFin(), evenement);
                    Prestation prestation = new Prestation(demandePrestataire.getDateDebut(), demandePrestataire.getDateFin(), prestataire, evenement);
                    memoire.getPrestations().add(prestation);
                    prestataire.getPrestations().add(prestation);
                    return true;
                }
            }
        }
        return false;
    }


}
