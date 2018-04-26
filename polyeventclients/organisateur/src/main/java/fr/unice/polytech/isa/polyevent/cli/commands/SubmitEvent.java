package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import fr.unice.polytech.isa.polyevent.stubs.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Token;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

public class SubmitEvent implements Command
{
    private static final Identifier IDENTIFIER = Identifier.SUBMIT_EVENT;
    private final Token token;
    private final Shell shell;
    private final Context context;
    private final DemanderEvenement demandeEvenement;
    private String name;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;
    private List<DemandeReservationSalle> demandeReservations;
    private List<DemandePrestataire> demandePrestataires;

    SubmitEvent(Token token, Shell shell, Context context, DemanderEvenement demandeEvenement)
    {
        this.token = token;
        this.shell = shell;
        this.context = context;
        this.demandeEvenement = demandeEvenement;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() != 3)
        {
            String message = String.format("%s expects 4 arguments: %s NOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }

        name = args.get(0);

        try
        {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            dateDebut = datatypeFactory.newXMLGregorianCalendar(args.get(1));
            dateFin = datatypeFactory.newXMLGregorianCalendar(args.get(2));
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(String.format("Illegal date format: %s", e.getMessage()));
        }

        demandeReservations = new ArrayList<>();
        demandePrestataires = new ArrayList<>();
    }

    @Override
    public void execute()
    {
        Shell subShell = shell.subShell();
        subShell.register(
                new Help.Builder(subShell),
                new AddReservation.Builder(demandeReservations),
                new AddService.Builder(demandePrestataires),
                new ValidateEvent.Builder(demandeEvenement, token, name, dateDebut, dateFin, demandeReservations, demandePrestataires),
                new CancelEvent.Builder()
        );
        context.out.format("Add reservations to the event \"%s\". Type ? for help.%n", name);
        subShell.run(context);
    }

    public static class Builder implements CommandBuilder<SubmitEvent>
    {
        private final Token token;
        private final Shell shell;
        private final DemanderEvenement demandeEvenement;

        public Builder(Token token, Shell shell, DemanderEvenement demandeEvenement)
        {
            this.token = token;
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
            return new SubmitEvent(token, shell, context, demandeEvenement);
        }
    }
}
