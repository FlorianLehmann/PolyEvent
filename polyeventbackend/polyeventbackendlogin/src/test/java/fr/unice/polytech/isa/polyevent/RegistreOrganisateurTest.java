package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.exceptions.ClientDejaCreeException;
import fr.unice.polytech.isa.polyevent.entities.outils.Mail;
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
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class RegistreOrganisateurTest{
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClass(RegistreOrganisateur.class)
                .addPackage(Organisateur.class.getPackage())
                .addPackage(TrouverOrganisateur.class.getPackage())
                .addPackage(EnregistrerOrganisateur.class.getPackage())
                .addPackage(ClientDejaCreeException.class.getPackage())
                .addPackage(Mail.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @EJB
    private EnregistrerOrganisateur enregistrerOrganisateur;

    @EJB
    private TrouverOrganisateur trouverOrganisateur;

    private Organisateur bob;

    @Before
    public void ini(){
        bob = new Organisateur("bob@gmail.com");
    }


    @Test
    public void OrganisateurInconnue() {
        assertFalse(trouverOrganisateur.connexion("pierre@gmail.com").isPresent());
    }


    @Test
    @Transactional(TransactionMode.COMMIT)
    public void registerCustomer() throws Exception {
        enregistrerOrganisateur.enregistrer(bob.getMail());
        Optional<Organisateur> organisateur = trouverOrganisateur.connexion(bob.getMail());
        assertTrue(organisateur.isPresent());
        Organisateur retrieved = organisateur.get();
        assertEquals(bob.getMail(), retrieved.getMail());
    }

}
