package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.DemanderFacture;
import fr.unice.polytech.isa.polyevent.stubs.Token;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public class Invoice implements Command
{
    private static final Identifier IDENTIFIER = Identifier.INVOICE;
    private final Context context;
    private final Token token;
    private final DemanderFacture demanderFacture;
    private String name;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;

    Invoice(Context context, Token token, DemanderFacture demanderFacture)
    {
        this.context = context;
        this.token = token;
        this.demanderFacture = demanderFacture;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() != 3)
        {
            String message = String.format("%s expects 3 arguments: %s NOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
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
    }

    @Override
    public void execute() throws Exception
    {
        String invoice = demanderFacture.obtenirFacture(token, name, dateDebut, dateFin);
        context.out.format("Response from the server: %s%n", invoice);
    }

    public static class Builder implements CommandBuilder<Invoice>
    {
        private final Token token;
        private final DemanderFacture demanderFacture;

        public Builder(Token token, DemanderFacture demanderFacture)
        {
            this.token = token;
            this.demanderFacture = demanderFacture;
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
        public Invoice build(Context context)
        {
            return new Invoice(context, token, demanderFacture);
        }
    }
}
