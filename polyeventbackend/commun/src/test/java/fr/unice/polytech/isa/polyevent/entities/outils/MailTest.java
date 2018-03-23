package fr.unice.polytech.isa.polyevent.entities.outils;

import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.exceptions.MailInvalideException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MailTest {

    @Test(expected = MailInvalideException.class) public void shouldNotHaveNullMail() {
        Mail mail = new Mail(null);
    }

    @Test(expected = MailInvalideException.class) public void shouldNotHaveEmptyMail() {
        new Mail("");
    }

    @Test public void shouldNotBeAValidMail() {
        boolean exceptionTrigerred = true;

        try {
            new Mail("badmail.fr");
            exceptionTrigerred = false;
        } catch (MailInvalideException e) {
            exceptionTrigerred &= true;
        }

        try {
            new Mail("@badmail.fr");
            exceptionTrigerred = false;
        } catch (MailInvalideException e) {
            exceptionTrigerred &= true;
        }

        try {
            new Mail("@.badmail.fr");
            exceptionTrigerred = false;
        } catch (MailInvalideException e) {
            exceptionTrigerred &= true;
        }

        assertTrue(exceptionTrigerred);

    }

    @Test public void shouldBeValidMail() {
        new Mail("ffgfa@gmail.com");
    }

}