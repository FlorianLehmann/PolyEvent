package fr.unice.polytech.isa.polyevent.entities;

import fr.unice.polytech.isa.polyevent.entities.exceptions.MailInvalideException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganisateurTest {

    @Test
    public void ShouldBeSameOrganisateur() {
        Organisateur a = new Organisateur("toto@unice.fr");
        Organisateur b = new Organisateur("toto@unice.fr");
        assertEquals(a,b);
    }

    @Test
    public void ShouldNotBeSameOrganisateur() {
        Organisateur a = new Organisateur("alice@unice.fr");
        Organisateur b = new Organisateur("toto@unice.fr");
        assertNotEquals(a,b);
    }

}