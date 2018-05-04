package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProfilClient implements ObtenirProfilOrganisateur {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Evenement> obtenirEvenementsOrganisateur(Organisateur organisateur) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Evenement> criteria = builder.createQuery(Evenement.class);
        Root<Evenement> root =  criteria.from(Evenement.class);

        criteria.select(root).where(builder.equal(root.get("organisateur"), organisateur));
        TypedQuery<Evenement> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public Optional<Evenement> obtenirEvenementOrganisateur(Organisateur organisateur, String nom, Date dateDebut, Date dateFin) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Evenement> criteria = builder.createQuery(Evenement.class);
        Root<Evenement> root =  criteria.from(Evenement.class);

        criteria.select(root).where(
                builder.equal(root.get("organisateur"), organisateur),
                builder.equal(root.get("nom"), nom),
                builder.equal(root.get("debut"), dateDebut),
                builder.equal(root.get("fin"), dateFin));
        TypedQuery<Evenement> query = entityManager.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }
}
