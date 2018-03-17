package fr.unice.polytech.isa.polyevent.webservice;

import fr.unice.polytech.isa.polyevent.component.DemandeReservation;
import org.junit.Before;
import org.junit.Test;
import javax.ejb.EJB;
import static org.junit.Assert.*;

public class demandeReservationTest {

    @EJB(name = "DemandeReservation-stateless") private DemandeReservation demandeReservation;

    @Before
    public void setUpContext() {
        demandeReservation = new DemandeReservation();
    }

    @Test
    public void validerReservationTest(){
        assertTrue(demandeReservation.accepterReservation());
    }


}
