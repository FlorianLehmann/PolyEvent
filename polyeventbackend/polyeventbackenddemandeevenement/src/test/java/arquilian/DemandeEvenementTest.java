package arquilian;

import fr.unice.polytech.isa.polyevent.DemanderReservation;
import fr.unice.polytech.isa.polyevent.HyperPlanningAPI;
import fr.unice.polytech.isa.polyevent.ValiderReservation;
import fr.unice.polytech.isa.polyevent.component.DemandeReservation;
import fr.unice.polytech.isa.polyevent.entities.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.TypeSalle;
import fr.unice.polytech.isa.polyevent.entities.outils.Mail;
import fr.unice.polytech.isa.polyevent.utils.Database;
import fr.unice.polytech.isa.polyevent.webservice.DemandeEvenement;
import fr.unice.polytech.isa.polyevent.webservice.DemanderEvenement;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                .addPackage(DemandeEvenementTest.class.getPackage())

//                .addPackage(Evenement.class.getPackage())
                // Components interfaces
                //

                .addPackage(DemanderEvenement.class.getPackage())

                // Component implementation
                .addPackage(DemandeEvenement.class.getPackage());
    }

    @EJB private DemanderReservation demandeReservation;
    @EJB private DemanderEvenement demanderEvenement;
    @EJB private Database memory;

    @Before public void flushDatabase() {
        memory.flush();

    }

    @Test public void noEvenementByDefault() {
        assertEquals(0, memory.getEvenements().size());
    }

    @Test public void shouldCreateAnEvent() {
        final String mail = "jean@f.com";
        Organisateur organisateur = new Organisateur(mail);
        demanderEvenement.demanderCreationEvenement(organisateur, "hashcode", new Date(), new Date(), new ArrayList<>());
        assertEquals(new Mail(mail), memory.getEvenements().get(0).getOrganisateur().getMail());
    }

    @Test public void shouldGetEventsFromOrganisateur() {
        final String mail = "jean@f.com";
        Organisateur organisateur = new Organisateur(mail);
        demanderEvenement.demanderCreationEvenement(organisateur, "hashcode", new Date(), new Date(), new ArrayList<>());
        assertEquals(demanderEvenement.getEvenements(organisateur).get(0), memory.getEvenements().get(0));

    }


    @Test public void testDEmandeReservation() {
       final String mail = "jean@f.com";
        Organisateur organisateur = new Organisateur(mail);
        List<DemandeReservationSalle> list = new ArrayList<>();
        HyperPlanningAPI mocked = mock(HyperPlanningAPI.class);
        demandeReservation.setHyperPlanningAPI(mocked);
        when(mocked.reserverSalle(any(), any())).thenReturn(true);
        list.add(new DemandeReservationSalle(new Date(), new Date(), TypeSalle.AMPHI));
        demanderEvenement.demanderCreationEvenement(organisateur, "hashcode", new Date(), new Date(),list);
        assertEquals(memory.getReservations().size(), 1);
    }

}
