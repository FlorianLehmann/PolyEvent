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
import java.util.List;
import java.util.Optional;

@Stateless
public class ProfilClient implements ObtenirProfilOrganisateur {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Evenement> obtenirEvenementOrganisateur(Organisateur organisateur) {
        return organisateur.getEvenements();
    }
}
