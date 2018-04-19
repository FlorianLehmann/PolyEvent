package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import fr.unice.polytech.isa.polyevent.stubs.*;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

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
        Context context = new Context(new Scanner(inputStream), printStream, false);
        SubmitEvent submitEvent = new SubmitEvent(shell, context, new DemanderEvenement()
        {
            @Override
            public List<Evenement> getEvenements(Organisateur organisateur)
            {
                return null;
            }

            @Override
            public void demanderCreationEvenement(Organisateur organisateur, String nom, XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin, List<DemandeReservationSalle> demandeReservations, List<DemandePrestataire> demandePrestataire)
            {
                assertEquals("XXX", organisateur.getMail().getMail());
                assertEquals("YYY", nom);
                assertEquals(SubmitEventTest.this.dateDebut, dateDebut);
                assertEquals(SubmitEventTest.this.dateFin, dateFin);
                assertEquals(new ArrayList<>(), demandeReservations);
                assertEquals(new ArrayList<>(), demandePrestataire);
            }
        });
        submitEvent.process(Arrays.asList(mail.getMail(), nom, dateDebut.toString(), dateFin.toString()));
    }
}