package fr.unice.polytech.isa.polyevent.components;

import org.junit.Before;
import org.junit.Test;
import javax.ejb.EJB;
import static org.junit.Assert.*;

public class demandeReservationTest {

    @EJB(name = "demandeReservation-stateless") private demandeReservation demandeReservation;

    @Before
    public void setUpContext() {
        demandeReservation = new demandeReservation();
    }

    @Test
    public void validerReservationTest(){
        assertTrue(demandeReservation.accepterReservation());
    }


}
