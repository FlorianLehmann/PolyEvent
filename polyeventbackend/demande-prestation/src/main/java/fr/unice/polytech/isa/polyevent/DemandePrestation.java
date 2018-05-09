package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Prestataire;
import fr.unice.polytech.isa.polyevent.entities.Prestation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class DemandePrestation implements DemanderPrestation {

    @EJB
    private EnvoyerMail envoyerMail;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean ajouterService(Evenement evenement, List<DemandePrestataire> demandePrestataires) {



        for (DemandePrestataire demandePrestataire: demandePrestataires) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Prestataire> criteria = builder.createQuery(Prestataire.class);
            Root<Prestataire> root =  criteria.from(Prestataire.class);

            criteria.select(root).where(builder.equal(root.get("typeService"), demandePrestataire.getTypeService()));
            TypedQuery<Prestataire> query = entityManager.createQuery(criteria);

            Prestataire prestataire;

            List<Prestataire> prestataires = query.getResultList();

            if (prestataires.isEmpty())
                return false;

            prestataire = prestataires.get(0);


            envoyerMail.envoieMail(prestataire, demandePrestataire.getDateDebut(), demandePrestataire.getDateFin(), evenement);
            Prestation prestation = new Prestation(demandePrestataire.getDateDebut(), demandePrestataire.getDateFin(), prestataire, evenement);
            entityManager.persist(prestation);
            prestataire.getPrestations().add(prestation);
        }
        return true;

    }


}
