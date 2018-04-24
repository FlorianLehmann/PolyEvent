package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
public class DemandePrestationTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DemandePrestation.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(EnvoyerMail.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");

    }

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private DemanderPrestation demanderPrestation;

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void ajoutPrestation(){
        entityManager.persist(new Prestataire("café@gmail.com", TypeService.CAFE));
        entityManager.persist(new Prestataire("bus@gmail.com", TypeService.CAFE));
        Organisateur organisateur = new Organisateur("organisateur@gmail.com");
        entityManager.persist(organisateur);
        Evenement evenement = new Evenement("Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2),
                organisateur, null, new StatusHistorique() );
        entityManager.persist(evenement);
        TypeService typeService = TypeService.CAFE;
        DemandePrestataire demandePrestataire = new DemandePrestataire(typeService, new Date(), new Date());
        List<DemandePrestataire> demandePrestataires = new ArrayList<DemandePrestataire>();
        demandePrestataires.add(demandePrestataire);
        demanderPrestation.ajouterService(evenement, demandePrestataires);
        //assertEquals(memory.getPrestations().size(),1);

    }

}
