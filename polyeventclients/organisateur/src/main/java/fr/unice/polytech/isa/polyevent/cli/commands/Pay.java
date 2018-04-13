package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.DemandeReservationSalle;
import fr.unice.polytech.isa.polyevent.stubs.Mail;
import fr.unice.polytech.isa.polyevent.stubs.Organisateur;
import fr.unice.polytech.isa.polyevent.stubs.PayerEvenement;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public class Pay implements Command
{
    private static final Identifier IDENTIFIER = Identifier.PAY;
    private final Context context;
    private final PayerEvenement payerEvenement;
    private Organisateur organisateur;
    private String nom;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;
    private List<DemandeReservationSalle> demandeReservations;
    private String creditCard;

    public Pay(Context context, PayerEvenement payerEvenement)
    {
        this.context = context;
        this.payerEvenement = payerEvenement;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() != 5)
        {
            String message = String.format("%s expects 5 arguments: %s  CREDIT_CARD MAIL NOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        creditCard = args.get(0);
        organisateur = new Organisateur();
        Mail mail = new Mail();
        mail.setMail(args.get(1));
        organisateur.setMail(mail);
        nom = args.get(2);

        try
        {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            dateDebut = datatypeFactory.newXMLGregorianCalendar(args.get(3));
            dateFin = datatypeFactory.newXMLGregorianCalendar(args.get(4));
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(String.format("Illegal date format: %s", e.getMessage()));
        }
    }

    @Override
    public void execute() throws Exception
    {
        String status = payerEvenement.payerEvenement(organisateur, nom, dateDebut, dateFin, creditCard);

        switch (status)
        {
            case "OK":
                context.out.println("You paid for the event " + nom + ". Thank you for using our service.");
                break;
            default:
                context.out.println("An error occurred while paying your event: " + status);
        }
    }

    public static class Builder implements CommandBuilder<Pay>
    {
        private final PayerEvenement payerEvenement;

        public Builder(PayerEvenement payerEvenement)
        {
            this.payerEvenement = payerEvenement;
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
        public Pay build(Context context)
        {
            return new Pay(context, payerEvenement);
        }
    }
}
