package fr.unice.polytech.isa.polyevent.cli.commands;

import com.sun.xml.internal.ws.client.ClientTransportException;
import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Organisateur;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.PrintStream;
import java.net.ConnectException;
import java.util.List;

public class ValidateEvent implements Command
{
    private static final String IDENTIFIER = "validate";
    private final DemanderEvenement demandeEvenement;
    private final Organisateur organisateur;
    private final String nom;
    private final XMLGregorianCalendar dateDebut;
    private final XMLGregorianCalendar dateFin;
    private final List<DemandeReservationSalle> demandeReservations;
    private final PrintStream out;

    public ValidateEvent(DemanderEvenement demandeEvenement, Organisateur organisateur, String nom,
                         XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin,
                         List<DemandeReservationSalle> demandeReservations, PrintStream out)
    {
        this.demandeEvenement = demandeEvenement;
        this.organisateur = organisateur;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.demandeReservations = demandeReservations;
        this.out = out;
    }

    @Override
    public void execute() throws Exception
    {
        out.format("%s submitted a new event \"%s\" between %tc and %tc with the following reservations:%n",
                organisateur.getMail().getMail(), nom, dateDebut.toGregorianCalendar(), dateFin.toGregorianCalendar());
        out.print(demandeReservations.stream()
                .map(reservation -> String.format("  - %s between %tc and %tc%n",
                        reservation.getTypeSalle(), reservation.getDateDebut().toGregorianCalendar(), reservation.getDateFin().toGregorianCalendar()))
                .reduce(String::concat)
                .orElse("\n"));

        try
        {
            demandeEvenement.demanderCreationEvenement(organisateur, nom, dateDebut, dateFin, demandeReservations);
        }
        catch (ClientTransportException e)
        {
            throw new ConnectException("Could not connect to the server. Check your internet connection and retry");
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return false;
    }

    public static class Builder implements CommandBuilder<ValidateEvent>
    {
        private final DemanderEvenement demandeEvenement;
        private final Organisateur organisateur;
        private final String nom;
        private final XMLGregorianCalendar dateDebut;
        private final XMLGregorianCalendar dateFin;
        private final List<DemandeReservationSalle> demandeReservations;
        private final PrintStream out;

        public Builder(DemanderEvenement demandeEvenement, Organisateur organisateur, String nom,
                       XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin,
                       List<DemandeReservationSalle> demandeReservations, PrintStream out)
        {
            this.demandeEvenement = demandeEvenement;
            this.organisateur = organisateur;
            this.nom = nom;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.demandeReservations = demandeReservations;
            this.out = out;
        }

        @Override
        public String identifier()
        {
            return IDENTIFIER;
        }

        @Override
        public String describe()
        {
            return "validate the event submission";
        }

        @Override
        public ValidateEvent build()
        {
            return new ValidateEvent(demandeEvenement, organisateur, nom, dateDebut, dateFin, demandeReservations, out);
        }
    }
}
