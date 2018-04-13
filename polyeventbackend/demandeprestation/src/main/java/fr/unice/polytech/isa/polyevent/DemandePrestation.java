package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Prestataire;
import fr.unice.polytech.isa.polyevent.entities.Prestation;
import fr.unice.polytech.isa.polyevent.entities.TypeService;
import fr.unice.polytech.isa.polyevent.utils.Database;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class DemandePrestation implements DemanderPrestation {

    @EJB
    private Database memoire;

    @EJB
    private EnvoyerMail envoyerMail;

    @Override
    public boolean ajouterService(Evenement evenement, TypeService typeService, Date dateDebut, Date dateFin) {
        for (Prestataire prestataire : memoire.getPrestataires()) {
            if(typeService.equals(prestataire.getTypeService())){
                envoyerMail.envoieMail(prestataire, dateDebut, dateFin, evenement);
                Prestation prestation = new Prestation(dateDebut, dateFin, prestataire, evenement);
                memoire.getPrestations().add(prestation);
                prestataire.getPrestations().add(prestation);
                return true;
            }
        }
        return false;
    }


}
