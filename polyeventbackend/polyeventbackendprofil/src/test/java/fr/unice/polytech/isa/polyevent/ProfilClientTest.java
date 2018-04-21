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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class ProfilClientTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ProfilClient.class)
                .addPackage(Organisateur.class.getPackage())
                .addPackage(Evenement.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @EJB
    private ObtenirProfilOrganisateur obtenirProfilOrganisateur;

    @PersistenceContext
    private EntityManager entityManager;

    private Organisateur bob;

    @Before
    public void ini(){
        bob = new Organisateur("bob@gmail.com");
        entityManager.persist(bob);
    }

    @Test
    public void OrganisateurInconnue(){
        assertEquals(obtenirProfilOrganisateur.obtenirEvenementOrganisateur(new Organisateur("patrick666@gmail.com")).get().size(),0);
    }

    @Transactional(TransactionMode.ROLLBACK)
    @Test
    public void OrganisateurAvecEvenement(){
        Evenement evenement = new Evenement("Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2),
                bob, null, new StatusHistorique() );
        entityManager.persist(evenement);

        assertTrue(obtenirProfilOrganisateur.obtenirEvenementOrganisateur(bob).isPresent());
        assertEquals(obtenirProfilOrganisateur.obtenirEvenementOrganisateur(bob).get().size(),1);

        Evenement evenement2 = new Evenement("Evenement2", new Date(2018, 5, 1), new Date(2018, 5, 2),
                bob, null, new StatusHistorique() );
        entityManager.persist(evenement2);
        assertEquals(obtenirProfilOrganisateur.obtenirEvenementOrganisateur(bob).get().size(),2);


    }

}
