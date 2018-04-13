package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Mail;
import fr.unice.polytech.isa.polyevent.stubs.Organisateur;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubmitEvent implements Command
{
    private static final Identifier IDENTIFIER = Identifier.SUBMIT_EVENT;
    private final Shell shell;
    private final Context context;
    private final DemanderEvenement demandeEvenement;
    private Organisateur organisateur;
    private String nom;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;
    private List<DemandeReservationSalle> demandeReservations;

    public SubmitEvent(Shell shell, Context context, DemanderEvenement demandeEvenement)
    {
        this.shell = shell;
        this.context = context;
        this.demandeEvenement = demandeEvenement;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() != 4)
        {
            String message = String.format("%s expects 4 arguments: %s MAIL NOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        organisateur = new Organisateur();
        Mail mail = new Mail();
        mail.setMail(args.get(0));
        organisateur.setMail(mail);
        nom = args.get(1);

        try
        {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            dateDebut = datatypeFactory.newXMLGregorianCalendar(args.get(2));
            dateFin = datatypeFactory.newXMLGregorianCalendar(args.get(3));
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(String.format("Illegal date format: %s", e.getMessage()));
        }

        demandeReservations = new ArrayList<>();
    }

    @Override
    public void execute()
    {
        Shell subShell = shell.subShell();
        subShell.register(
                new Help.Builder(subShell),
                new AddReservation.Builder(demandeReservations),
                new ValidateEvent.Builder(demandeEvenement, organisateur, nom, dateDebut, dateFin, demandeReservations),
                new CancelEvent.Builder()
        );
        context.out.format("Add reservations to the event \"%s\". Type ? for help.%n", nom);
        subShell.run(context);
    }

    public static class Builder implements CommandBuilder<SubmitEvent>
    {
        private final Shell shell;
        private final DemanderEvenement demandeEvenement;

        public Builder(Shell shell, DemanderEvenement demandeEvenement)
        {
            this.shell = shell;
            this.demandeEvenement = demandeEvenement;
        }

        @Override
        public String identifier()
        {
            return IDENTIFIER.keyword;
        }

        @Override
        public String describe()
        {
            return IDENTIFIER.description;
        }

        @Override
        public String help()
        {
            return String.format("Usage: %s MAIL NOM START_DATE END_DATE%n" +
                            "Example: %s marcel@etu.unice.com \"La nuit de l'info\" 2018-12-03T16:00:00 2018-12-04T08:00:00",
                    IDENTIFIER, IDENTIFIER);
        }

        @Override
        public SubmitEvent build(Context context)
        {
            return new SubmitEvent(shell, context, demandeEvenement);
        }
    }
}
