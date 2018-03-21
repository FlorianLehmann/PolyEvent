package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.TypeSalle;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public class AddReservation implements Command
{
    private static final String IDENTIFIER = "reservation";
    private final List<DemandeReservationSalle> demandeReservations;
    private TypeSalle typeSalle;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;

    private AddReservation(List<DemandeReservationSalle> demandeReservations)
    {
        this.demandeReservations = demandeReservations;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() < 3)
        {
            String message = String.format("%s expects 3 arguments: %s TYPE_ROOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        typeSalle = TypeSalle.valueOf(args.get(0));
        try
        {
            dateDebut = DatatypeFactory.newInstance().newXMLGregorianCalendar(args.get(1));
            dateFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(args.get(2));
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(String.format("Illegal date format: %s", e.getMessage()));
        }
    }

    @Override
    public void execute() throws Exception
    {
        DemandeReservationSalle demandeReservationSalle = new DemandeReservationSalle();
        demandeReservationSalle.setTypeSalle(typeSalle);
        demandeReservationSalle.setDateDebut(dateDebut);
        demandeReservationSalle.setDateFin(dateFin);
        demandeReservations.add(demandeReservationSalle);
    }

    public static class Builder implements CommandBuilder<AddReservation>
    {
        private final List<DemandeReservationSalle> demandeReservations;

        public Builder(List<DemandeReservationSalle> demandeReservations)
        {
            this.demandeReservations = demandeReservations;
        }

        @Override
        public String identifier()
        {
            return IDENTIFIER;
        }

        @Override
        public String describe()
        {
            return "Add a reservation to the event";
        }

        @Override
        public String help()
        {
            return String.format("Usage: %s TYPE_ROOM START_DATE END_DATE%n" +
                    "Example: %s AMPHI 2018-12-03T16:00:00 2018-12-03T18:00:00", IDENTIFIER, IDENTIFIER);
        }

        @Override
        public AddReservation build()
        {
            return new AddReservation(demandeReservations);
        }
    }
}
