package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.PayerEvenement;
import fr.unice.polytech.isa.polyevent.stubs.Token;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public class Pay implements Command
{
    private static final Identifier IDENTIFIER = Identifier.PAY;
    private final Token token;
    private final Context context;
    private final PayerEvenement payerEvenement;
    private String nom;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;
    private String creditCard;

    public Pay(Token token, Context context, PayerEvenement payerEvenement)
    {
        this.token = token;
        this.context = context;
        this.payerEvenement = payerEvenement;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() != 4)
        {
            String message = String.format("%s expects 4 arguments: %s CREDIT_CARD NOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        creditCard = args.get(0);
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
    }

    @Override
    public void execute()
    {
        String status = payerEvenement.payerEvenement(token, nom, dateDebut, dateFin, creditCard);

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
        private final Token token;
        private final PayerEvenement payerEvenement;

        public Builder(Token token, PayerEvenement payerEvenement)
        {
            this.token = token;
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
            return new Pay(token, context, payerEvenement);
        }
    }
}
