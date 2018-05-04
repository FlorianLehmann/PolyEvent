package fr.unice.polytech.isa.polyevent.component;

import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.HyperPlanningAPI;
import fr.unice.polytech.isa.polyevent.ValiderReservation;
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class DemandeReservationTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DemandeReservation.class)
                .addClass(ValiderReservation.class)
                .addClass(DemanderReservation.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");

    }

    @EJB private DemanderReservation demanderReservation;
    @EJB private ValiderReservation validerReservation;

    @PersistenceContext private EntityManager entityManager;

    private Evenement evenement;
    private List<DemandeReservationSalle> demandeReservationSalles;


    @Before
    public void init() {
        entityManager.createQuery("DELETE FROM Reservation").executeUpdate();
        initData();
        iniMock();
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void reserverAmphi(){
        DemandeReservationSalle demandeReservationAmphi = new DemandeReservationSalle(new Date(2018, 4, 1, 10, 0), new Date(2018, 4, 2, 12, 0),
                TypeSalle.AMPHI );
        demandeReservationSalles.add(demandeReservationAmphi);

        demanderReservation.demanderReservationSalle(evenement,demandeReservationSalles);

        List<Reservation> reservations = validerReservation.getReservations();
        Reservation reservation = reservations.get(0);
        assertEquals(reservation.getDateDebut(), demandeReservationAmphi.getDateDebut());
        assertEquals(reservation.getDateFin(), demandeReservationAmphi.getDateFin());
        assertEquals(reservation.getEvenement(), evenement);
        assertEquals(reservation.getTypeSalle(), demandeReservationAmphi.getTypeSalle());
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void shouldValidReservation() {
        Reservation reservation = new Reservation(new Date(2018, 4, 1, 10, 0), new Date(2018, 4, 2, 12, 0),
                TypeSalle.AMPHI, null, evenement, Statut.EN_ATTENTE_DE_VALIDATION);
        entityManager.persist(reservation);

        validerReservation.accepterReservation(reservation,new Salle("O+311"));

        assertEquals(validerReservation.getReservations().get(0).getStatut(), Statut.VALIDE);
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void shouldRefuseReservation() {
        Reservation reservation = new Reservation(new Date(2018, 4, 1, 10, 0), new Date(2018, 4, 2, 12, 0),
                TypeSalle.AMPHI, null, evenement, Statut.EN_ATTENTE_DE_VALIDATION);
        entityManager.persist(reservation);

        validerReservation.refuserReservation(reservation, "plus de salles dispo");

        assertEquals(validerReservation.getReservations().get(0).getStatut(), Statut.REFUSE);
    }



    private void initData(){
        Organisateur organisateur = new Organisateur("organisateur@gmail.com");
        demandeReservationSalles = new ArrayList<>();
        evenement = new Evenement("Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2),
                organisateur, null, Statut.EN_ATTENTE_DE_VALIDATION );
    }

    private void iniMock() {
        HyperPlanningAPI mocked = mock(HyperPlanningAPI.class);
        demanderReservation.setHyperPlanningAPI(mocked);
        List<String> strings = new LinkedList<>();
        strings.add("0+300");
        when(mocked.reserverSalle(any(), any())).thenReturn("Succès");
        try {
            when(mocked.DemandeSallesDisponible(any())).thenReturn(strings);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
