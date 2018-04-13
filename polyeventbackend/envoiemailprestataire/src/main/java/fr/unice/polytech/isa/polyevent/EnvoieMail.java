package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Prestataire;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class EnvoieMail implements EnvoyerMail {

    @Override
    public void envoieMail(Prestataire prestataire, Date dateDebut, Date dataFin, Evenement evenement) {
    }
}
