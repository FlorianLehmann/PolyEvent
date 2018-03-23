package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Mail;
import fr.unice.polytech.isa.polyevent.stubs.Organisateur;
import jdk.internal.util.xml.impl.Input;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class SubmitEventTest
{
    private Organisateur organisateur;
    private Mail mail;
    private PrintStream printStream;
    private ByteArrayInputStream inputStream;
    private Shell shell;
    private String nom;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;

    @Before
    public void setUp() throws Exception
    {
        shell = new Shell();
        inputStream = new ByteArrayInputStream("validate".getBytes());
        printStream = new PrintStream(new ByteArrayOutputStream());
        mail = new Mail();
        mail.setMail("XXX");
        organisateur = new Organisateur();
        organisateur.setMail(mail);
        nom = "YYY";
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        dateDebut = datatypeFactory.newXMLGregorianCalendar("2018");
        dateFin = datatypeFactory.newXMLGregorianCalendar("2019");
    }

    @Test
    public void emptyArrayIsNotNull() throws Exception
    {
        SubmitEvent submitEvent = new SubmitEvent(shell, inputStream, printStream, new DemanderEvenement()
        {
            @Override
            public void demanderCreationEvenement(Organisateur organisateur, String nom, XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin, List<DemandeReservationSalle> demandeReservations)
            {
                assertEquals("XXX", organisateur.getMail().getMail());
                assertEquals("YYY", nom);
                assertEquals(SubmitEventTest.this.dateDebut, dateDebut);
                assertEquals(SubmitEventTest.this.dateFin, dateFin);
                assertEquals(new ArrayList<>(), demandeReservations);
            }
        });
        submitEvent.process(Arrays.asList(mail.getMail(), nom, dateDebut.toString(), dateFin.toString()));
    }
}