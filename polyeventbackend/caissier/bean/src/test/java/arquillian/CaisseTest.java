package arquillian;

import fr.unice.polytech.isa.polyevent.ChercherEvenement;
import fr.unice.polytech.isa.polyevent.caissier.bean.Caisse;
import fr.unice.polytech.isa.polyevent.caissier.bean.Caissier;
import fr.unice.polytech.isa.polyevent.caissier.bean.StatusCaisse;
import fr.unice.polytech.isa.polyevent.comptable.bean.Facturer;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.Statut;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CaisseTest
{
    @EJB
    private Caisse caisse;
    @PersistenceContext
    private EntityManager entityManager;

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Caissier.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(ChercherEvenement.class.getPackage())
                .addPackage(Facturer.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void payerEvenement()
    {
        Organisateur organisateur = new Organisateur("organisateur@gmail.com");
        entityManager.persist(organisateur);
        Evenement evenement = new Evenement("Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2),
                organisateur, null, Statut.EN_ATTENTE_DE_VALIDATION );
        entityManager.persist(evenement);
        StatusCaisse status = caisse.payer(organisateur, "Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2), "12345678");
        assertEquals(StatusCaisse.OK, status);
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void payerEvenementIntrouvable()
    {
        Organisateur organisateur = new Organisateur("organisateur@gmail.com");
        entityManager.persist(organisateur);
        StatusCaisse status = caisse.payer(organisateur, "Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2), "12345678");
        assertEquals(StatusCaisse.EVENEMENT_INTROUVABLE, status);
    }
}
