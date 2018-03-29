package fr.unice.polytech.isa.polyevent.component;

import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.HyperPlanningAPI;
import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class DemandeReservationTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DemandeReservation.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addPackage(Database.class.getPackage());
    }

    @EJB private DemanderReservation demanderReservation;

    @EJB private Database memory;

    private Evenement evenement;
    private List<DemandeReservationSalle> demandeReservationSalles;


    @Before
    public void init() {
        memory.flush();
        initData();
        iniMock();
        flushDatabase();
    }

    @Test
    public void reserverAmphi(){
        DemandeReservationSalle demandeReservationAmphi = new DemandeReservationSalle(new Date(2018, 4, 1, 10, 0), new Date(2018, 4, 2, 12, 0),
                TypeSalle.AMPHI );
        demandeReservationSalles.add(demandeReservationAmphi);

        demanderReservation.demanderReservationSalle(evenement,demandeReservationSalles);
        assertTrue(memory.getReservations().size()==1);
        assertTrue(memory.getReservations().get(0).getStatut().equals(Statut.VALIDE));
    }

    @Test
    public void reserverPlusieursSalleMemeHoraire(){
        DemandeReservationSalle demandeReservationSalle1 = new DemandeReservationSalle(new Date(2018, 4, 1, 10, 0), new Date(2018, 4, 2, 12, 0),
                TypeSalle.SALLE );
        DemandeReservationSalle demandeReservationSalle2 = new DemandeReservationSalle(new Date(2018, 4, 1, 10, 0), new Date(2018, 4, 2, 12, 0),
                TypeSalle.SALLE );
        demandeReservationSalles.add(demandeReservationSalle1);
        demandeReservationSalles.add(demandeReservationSalle2);

        demanderReservation.demanderReservationSalle(evenement,demandeReservationSalles);
        assertTrue(memory.getReservations().size()==2);
        assertTrue(memory.getReservations().get(0).getStatut().equals(Statut.VALIDE));
        assertTrue(memory.getReservations().get(1).getStatut().equals(Statut.VALIDE));
        List<Salle> salles = new ArrayList<>();

        for (Reservation reservation:memory.getReservations()) {
            if(!salles.contains(reservation.getSalle())){
                salles.add(reservation.getSalle());
            }
        }

        assertTrue(salles.size()==2);

    }

    public void flushDatabase() { memory.flush(); }

    private void initData(){
        Organisateur organisateur = new Organisateur("organisateur@gmail.com");
        demandeReservationSalles = new ArrayList<>();
        evenement = new Evenement("Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2),
                organisateur, null, new StatusHistorique() );
    }

    private void iniMock(){
        HyperPlanningAPI mocked = mock(HyperPlanningAPI.class);
        demanderReservation.setHyperPlanningAPI(mocked);
        when(mocked.reserverSalle(any(), any())).thenReturn(true);
    }

}
