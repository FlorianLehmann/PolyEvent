package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.stubs.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

public class SubmitEvent implements Command
{
    private static final String IDENTIFIER = "event";
    private final DemanderEvenement demandeEvenement;
    private Organisateur organisateur;
    private String nom;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;
    private List<DemandeReservationSalle> demandeReservations;

    private SubmitEvent(DemanderEvenement demandeEvenement)
    {
        this.demandeEvenement = demandeEvenement;
    }

    @Override
    public void load(String... args) throws Exception
    {
        if (args.length < 5)
        {
            String message = String.format("%s expect at least 5 arguments: %s organisateur nom debut fin [reservations+]", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        organisateur = new Organisateur();
        Mail mail = new Mail();
        mail.setMail(args[0]);
        organisateur.setMail(mail);
        nom = args[1];
        dateDebut = DatatypeFactory.newInstance().newXMLGregorianCalendar(args[2]);
        dateFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(args[3]);

        demandeReservations = new ArrayList<>();
        for (int i = 4; i < args.length; i++)
        {
            DemandeReservationSalle reservation = new DemandeReservationSalle();
            reservation.setTypeSalle(TypeSalle.valueOf(args[i]));
            demandeReservations.add(reservation);
        }
    }

    @Override
    public void execute() throws Exception
    {
        demandeEvenement.demanderCreationEvenement(organisateur, nom, dateDebut, dateFin, demandeReservations);
    }

    @Override
    public boolean shouldContinue()
    {
        return true;
    }

    public static class Builder implements CommandBuilder<SubmitEvent>
    {
        private final DemanderEvenement demandeEvenement;

        public Builder(DemanderEvenement demandeEvenement)
        {
            this.demandeEvenement = demandeEvenement;
        }

        @Override
        public String identifier()
        {
            return IDENTIFIER;
        }

        @Override
        public String describe()
        {
            return "Submit an event to the system";
        }

        @Override
        public SubmitEvent build()
        {
            return new SubmitEvent(demandeEvenement);
        }
    }
}
