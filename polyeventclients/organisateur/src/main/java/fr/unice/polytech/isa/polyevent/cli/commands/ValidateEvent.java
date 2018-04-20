package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.DemanderEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Organisateur;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.util.List;

public class ValidateEvent implements Command
{
    private static final Identifier IDENTIFIER = Identifier.VALIDATE;
    private final DemanderEvenement demandeEvenement;
    private final Organisateur organisateur;
    private final String nom;
    private final XMLGregorianCalendar dateDebut;
    private final XMLGregorianCalendar dateFin;
    private final List<DemandeReservationSalle> demandeReservations;
    private final List<DemandePrestataire> demandePrestataires;
    private final PrintStream out;

    public ValidateEvent(DemanderEvenement demandeEvenement, Organisateur organisateur, String nom,
                         XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin,
                         List<DemandeReservationSalle> demandeReservations, List<DemandePrestataire> demandePrestataires,
                         PrintStream out)
    {
        this.demandeEvenement = demandeEvenement;
        this.organisateur = organisateur;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.demandeReservations = demandeReservations;
        this.demandePrestataires = demandePrestataires;
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
            demandeEvenement.demanderCreationEvenement(organisateur, nom, dateDebut, dateFin, demandeReservations, demandePrestataires);
        }
        catch (WebServiceException e)
        {
            throw new ConnectException(String.format("Could not connect to the server. Check your internet connection and retry:%n%s", e.getMessage()));
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
        private final List<DemandePrestataire> demandePrestataires;

        Builder(DemanderEvenement demandeEvenement, Organisateur organisateur, String nom,
                XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin,
                List<DemandeReservationSalle> demandeReservations, List<DemandePrestataire> demandePrestataires)
        {
            this.demandeEvenement = demandeEvenement;
            this.organisateur = organisateur;
            this.nom = nom;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.demandeReservations = demandeReservations;
            this.demandePrestataires = demandePrestataires;
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
        public ValidateEvent build(Context context)
        {
            return new ValidateEvent(demandeEvenement, organisateur, nom, dateDebut, dateFin, demandeReservations, demandePrestataires, context.out);
        }
    }
}
