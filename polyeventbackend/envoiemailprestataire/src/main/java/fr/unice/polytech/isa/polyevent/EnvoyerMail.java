package fr.unice.polytech.isa.polyevent;
import fr.unice.polytech.isa.polyevent.entities.Prestataire;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import javax.ejb.Local;
import java.util.Date;

@Local
public interface EnvoyerMail {
    void envoieMail(Prestataire prestataire, Date dateDebut, Date dataFin, Evenement evenement);
}
