package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.StatusHistorique;
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

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class ChercheurEvenementTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ChercheurEvenement.class)
                .addPackage(Organisateur.class.getPackage())
                .addPackage(Evenement.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @EJB
    private ChercherEvenement chercherEvenement;

    @PersistenceContext
    private EntityManager entityManager;

    private Organisateur bob;
    private Date debut;
    private Date fin;
    private String nom;

    @Before
    public void ini(){
        bob = new Organisateur("bob@gmail.com");
        debut = new Date(2018, 4, 1);
        fin = new Date(2018, 4, 2);
        nom = "Evenement";
    }

    @Test
    public void evenementInconnu(){
        assertFalse(chercherEvenement.chercherEvenement(nom,debut,fin,bob).isPresent());
    }

    @Test
    public void evenementConnu(){
        Evenement evenement = new Evenement(nom, debut, fin, bob, null, new StatusHistorique());
        entityManager.persist(evenement);
        Optional<Evenement> evenementOptional = chercherEvenement.chercherEvenement(nom, debut, fin, bob);
        assertTrue(evenementOptional.isPresent());
        Evenement retrieved = evenementOptional.get();
        assertEquals(evenement.getId(), retrieved.getId());
    }



}
