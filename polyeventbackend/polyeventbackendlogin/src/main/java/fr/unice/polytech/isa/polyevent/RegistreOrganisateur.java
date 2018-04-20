package fr.unice.polytech.isa.polyevent;


import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientDejaCreeException;
import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;



@Stateless
public class RegistreOrganisateur implements TrouverOrganisateur, EnregistrerOrganisateur{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Organisateur> connexion(String mail) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organisateur> criteria = builder.createQuery(Organisateur.class);
        Root<Organisateur> root =  criteria.from(Organisateur.class);
        criteria.select(root).where(builder.equal(root.get("mail").get("mail"), mail));
        TypedQuery<Organisateur> query = entityManager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }

    }

    @Override
    public void enregistrer(String mail) throws ClientDejaCreeException {
        if(connexion(mail).isPresent())
            throw new ClientDejaCreeException(mail);

        Organisateur organisateur = new Organisateur(mail);
        entityManager.persist(organisateur);
    }
}
