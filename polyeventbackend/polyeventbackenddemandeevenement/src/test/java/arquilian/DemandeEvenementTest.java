package arquilian;

import fr.unice.polytech.isa.polyevent.DemanderPrestation;
import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.HyperPlanningAPI;
import fr.unice.polytech.isa.polyevent.ValiderReservation;
import fr.unice.polytech.isa.polyevent.component.DemandeReservation;
import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.entities.outils.Mail;
import fr.unice.polytech.isa.polyevent.utils.Database;
import fr.unice.polytech.isa.polyevent.webservice.DemandeEvenement;
import fr.unice.polytech.isa.polyevent.webservice.DemanderEvenement;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class DemandeEvenementTest {

    // Classes to package into a deployable unit used to run the test
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Business Objects
                .addPackage(Database.class.getPackage())
//                .addPackage(Organisateur.class.getPackage())
                .addPackage(DemanderReservation.class.getPackage())
                .addPackage(ValiderReservation.class.getPackage())
                .addPackage(DemandeReservation.class.getPackage())

//                .addPackage(Evenement.class.getPackage())
                // Components interfaces
                //

                .addPackage(DemanderEvenement.class.getPackage())

                // Component implementation
                .addPackage(DemandeEvenement.class.getPackage())
                .addPackage(DemanderPrestation.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");

    }


    @EJB private DemanderReservation demandeReservation;

    @EJB private DemanderEvenement demanderEvenement;
    @EJB private Database memory;

    @Before public void flushDatabase() { memory.flush(); }

    @Test public void noEvenementByDefault() {
        assertEquals(0, memory.getEvenements().size());
    }

    @Transactional(TransactionMode.COMMIT)
    @Test public void shouldCreateAnEvent() {
        final String mail = "jean@f.com";
        Organisateur organisateur = new Organisateur(mail);
        entityManager.persist(organisateur);
        demanderEvenement.demanderCreationEvenement(organisateur, "hashcode", new Date(), new Date(), new ArrayList<>(), null);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Evenement> criteria = builder.createQuery(Evenement.class);
        Root<Evenement> root =  criteria.from(Evenement.class);

        criteria.select(root).where(builder.equal(root.get("organisateur"), organisateur));
        TypedQuery<Evenement> query = entityManager.createQuery(criteria);

        Evenement stored = query.getSingleResult();

        assertEquals(stored.getOrganisateur(), organisateur);
    }

    @Transactional(TransactionMode.COMMIT)
    @Test public void shouldGetEventsFromOrganisateur() {
        final String mail = "jean@f.com";
        Organisateur organisateur = new Organisateur(mail);
        entityManager.persist(organisateur);
        demanderEvenement.demanderCreationEvenement(organisateur, "hashcode", new Date(), new Date(), new ArrayList<>(), null);
        assertEquals(demanderEvenement.getEvenements(organisateur).get(0), organisateur.getEvenements().get(0));

    }

    @Transactional(TransactionMode.COMMIT)
    @Test public void testDEmandeReservation() {
        final String mail = "jean@f.com";
        Organisateur organisateur = new Organisateur(mail);
        entityManager.persist(organisateur);
        HyperPlanningAPI mocked = mock(HyperPlanningAPI.class);
        demandeReservation.setHyperPlanningAPI(mocked);
        when(mocked.reserverSalle(any(), any())).thenReturn("Succès");
        List<DemandeReservationSalle> list = new ArrayList<>();
        list.add(new DemandeReservationSalle(new Date(), new Date(), TypeSalle.AMPHI));
        demanderEvenement.demanderCreationEvenement(organisateur, "hashcode", new Date(), new Date(),list, null);
        assertEquals(memory.getReservations().size(), 1);
    }


    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testOrganisateurStorage() throws Exception {
        Organisateur organisateur = new Organisateur();			// create an empty customer
        organisateur.setMail(new Mail("alice@unice.fr"));		// setting up details
        organisateur.setId(0);
        entityManager.persist(organisateur);				// making the entity persistent
        int id = organisateur.getId();
        assertNotEquals(0,id);					// an id was assigned by the persistence layer
        Organisateur stored = (Organisateur) entityManager.find(Organisateur.class, id);
        assertEquals(organisateur, stored);
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testEvenementStorage() throws Exception {

        Organisateur organisateur = new Organisateur();			// create an empty customer
        organisateur.setMail(new Mail("alicccce@unice.fr"));		// setting up details
        organisateur.setId(0);
        entityManager.persist(organisateur);				// making the entity persistent
        int id_organisateur = organisateur.getId();
        assertNotEquals(0,id_organisateur);					// an id was assigned by the persistence layer
        Organisateur stored = (Organisateur) entityManager.find(Organisateur.class, id_organisateur);
        assertEquals(organisateur, stored);


        Evenement evenement = new Evenement();			// create an empty customer
        evenement.setDebut(new Date(2018, 4, 1));
        evenement.setFin(new Date(2018, 4, 2));
        evenement.setNom("nuit info");
        evenement.setOrganisateur(stored);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(new Date(), new Date(), TypeSalle.AMPHI, new Salle("0310"), evenement, Statut.VALIDE ));
        evenement.setReservations(reservations);
        evenement.setStatusHistorique(new StatusHistorique());
        evenement.setId(0);
        entityManager.persist(evenement);				// making the entity persistent
        int id = evenement.getId();
        assertNotEquals(0,id);					// an id was assigned by the persistence layer
        Evenement evenement_stored = (Evenement) entityManager.find(Evenement.class, id);
        assertEquals(evenement, evenement_stored);
    }


    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testReservationStorage() throws Exception {

        Organisateur organisateur = new Organisateur();			// create an empty customer
        organisateur.setMail(new Mail("alicccce@unice.fr"));		// setting up details
        organisateur.setId(0);
        entityManager.persist(organisateur);				// making the entity persistent
        int id_organisateur = organisateur.getId();
        assertNotEquals(0,id_organisateur);					// an id was assigned by the persistence layer
        Organisateur stored = (Organisateur) entityManager.find(Organisateur.class, id_organisateur);
        assertEquals(organisateur, stored);


        Evenement evenement = new Evenement();			// create an empty customer
        evenement.setDebut(new Date(2018, 4, 1));
        evenement.setFin(new Date(2018, 4, 2));
        evenement.setNom("nuit info");
        evenement.setOrganisateur(stored);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(new Date(), new Date(), TypeSalle.AMPHI, new Salle("0310"), evenement, Statut.VALIDE ));
        evenement.setReservations(reservations);
        evenement.setStatusHistorique(new StatusHistorique());
        evenement.setId(0);
        entityManager.persist(evenement);				// making the entity persistent
        int id = evenement.getId();
        assertNotEquals(0,id);					// an id was assigned by the persistence layer
        Evenement evenement_stored = (Evenement) entityManager.find(Evenement.class, id);
        assertEquals(evenement, evenement_stored);

        Reservation reservation = new Reservation(new Date(), new Date(), TypeSalle.AMPHI, new Salle(), evenement_stored, Statut.EN_ATTENTE_DE_VALIDATION );			// create an empty customer
        entityManager.persist(reservation);				// making the entity persistent
        int reservationId = reservation.getId();
        assertNotEquals(0,id);					// an id was assigned by the persistence layer
        Reservation reservationStored = (Reservation) entityManager.find(Reservation.class, reservationId);
        assertEquals(reservation, reservationStored);
    }
}