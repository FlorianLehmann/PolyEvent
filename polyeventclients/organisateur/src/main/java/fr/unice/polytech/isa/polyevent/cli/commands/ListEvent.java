package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.*;

import java.io.PrintStream;
import java.util.List;

public class ListEvent implements Command
{
    private static final Identifier IDENTIFIER = Identifier.LIST_EVENT;
    private final PrintStream out;
    private final DemanderEvenement demandeEvenement;
    private Organisateur organisateur;

    ListEvent(PrintStream out, DemanderEvenement demandeEvenement)
    {
        this.out = out;
        this.demandeEvenement = demandeEvenement;
    }

    @Override
    public void load(List<String> args)
    {
        if (args.size() != 1)
        {
            String message = String.format("%s expects 1 argument: %s MAIL",
                    IDENTIFIER.keyword, IDENTIFIER.keyword);
            throw new IllegalArgumentException(message);
        }
        Mail mail = new Mail();
        mail.setMail(args.get(0));
        organisateur = new Organisateur();
        organisateur.setMail(mail);
    }

    @Override
    public void execute()
    {
        List<Evenement> events = demandeEvenement.getEvenements(organisateur);
        out.format("Listing events of %s:%n", organisateur.getMail().getMail());
        for (Evenement event : events)
        {
            out.format("  - Event \"%s\" between %tc and %tc%n", event.getNom(),
                    event.getDebut().toGregorianCalendar(),
                    event.getFin().toGregorianCalendar());

            for (Reservation reservation : event.getReservations())
            {
                printReservation(reservation);
            }
        }
    }

    private void printReservation(Reservation reservation)
    {
        switch (reservation.getStatut())
        {
            case VALIDE:
                out.format("      - Reserved room \"%s\" for %s between %tc and %tc%n",
                        reservation.getSalle().getNom(), reservation.getTypeSalle(),
                        reservation.getDateDebut().toGregorianCalendar(),
                        reservation.getDateFin().toGregorianCalendar());
                break;
            case EN_ATTENTE_DE_VALIDATION:
                out.format("      - Reservation for %s between %tc and %tc still in pending...%n",
                        reservation.getTypeSalle(),
                        reservation.getDateDebut().toGregorianCalendar(),
                        reservation.getDateFin().toGregorianCalendar());
                break;
            case REFUSE:
                out.format("      - Reservation for %s between %tc and %tc has been refused%n",
                        reservation.getTypeSalle(),
                        reservation.getDateDebut().toGregorianCalendar(),
                        reservation.getDateFin().toGregorianCalendar());
        }
    }

    public static class Builder implements CommandBuilder<ListEvent>
    {
        private final DemanderEvenement demandeEvenement;

        public Builder(DemanderEvenement demandeEvenement)
        {
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
            return String.format("Usage: %s MAIL%nExample: %s marcel@etu.unice.com", IDENTIFIER, IDENTIFIER);
        }

        @Override
        public ListEvent build(Context context)
        {
            return new ListEvent(context.out, demandeEvenement);
        }
    }
}
