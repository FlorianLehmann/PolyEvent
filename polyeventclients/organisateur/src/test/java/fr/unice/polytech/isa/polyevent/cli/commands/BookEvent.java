package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.Main;
import fr.unice.polytech.isa.polyevent.api.PolyEventAPI;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Mail;
import fr.unice.polytech.isa.polyevent.stubs.Organisateur;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookEvent
{
    private Organisateur organisateur;
    private Mail mail;
    private PrintStream printStream;
    private ByteArrayInputStream inputStream;
    private Shell shell;
    private String nom;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception
    {
        shell = new Shell();
        inputStream = new ByteArrayInputStream("play demo/demo1.pe".getBytes());
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        mail = new Mail();
        mail.setMail("XXX");
        organisateur = new Organisateur();
        organisateur.setMail(mail);
        nom = "YYY";
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        dateDebut = datatypeFactory.newXMLGregorianCalendar("2018");
        dateFin = datatypeFactory.newXMLGregorianCalendar("2019");
    }

    @Ignore
    @Test(timeout = 2000)
    public void submitEvent() throws Exception
    {
        Main.runCLI("polyevent-backend", "8080", inputStream, printStream);
    }
}
