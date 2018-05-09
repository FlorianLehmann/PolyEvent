package fr.unice.polytech.isa.polyevent.caissier.bean;

import fr.unice.polytech.isa.polyevent.ChercherEvenement;
import fr.unice.polytech.isa.polyevent.comptable.bean.Facturer;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.Statut;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.Optional;

@Stateless
public class Caissier implements Caisse
{
    @EJB
    private ChercherEvenement chercherEvenement;

    @EJB
    private Facturer facturer;

    @Override
    public StatusCaisse payer(Organisateur organisateur, String nom, Date dateDebut, Date dateFin, String creditCard)
    {
        Optional<Evenement> optional = chercherEvenement.chercherEvenement(nom, dateDebut, dateFin, organisateur);
        if (optional.isPresent())
        {
            Evenement evenement = optional.get();

            switch (this.facturer.facturer(evenement))
            {
                case PAYEMENT_EFFECTUER:
                    evenement.setStatut(Statut.PAYER);
                    return StatusCaisse.OK;
                default:
                    return StatusCaisse.ERREUR;
            }
        }
        return StatusCaisse.EVENEMENT_INTROUVABLE;
    }
}
