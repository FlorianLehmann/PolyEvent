package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.EnregistrerClient;

import java.util.List;

public class Register implements Command
{
    private static final Identifier IDENTIFIER = Identifier.REGISTER;
    private final Context context;
    private final EnregistrerClient enregistrerClient;
    private String mail;

    public Register(Context context, EnregistrerClient enregistrerClient)
    {
        this.context = context;
        this.enregistrerClient = enregistrerClient;
    }

    @Override
    public void load(List<String> args)
    {
        if (args.size() != 1)
        {
            String message = String.format("%s expects 1 argument: %s MAIL", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        mail = args.get(0);
    }

    @Override
    public void execute() throws Exception
    {
        enregistrerClient.enregistrerClient(mail);
        context.out.format("The account %s has been created.%n" +
                "You can now login into your account with the following command: login %s%n", mail, mail);
    }

    public static class Builder implements CommandBuilder<Register>
    {
        private final EnregistrerClient enregistrerClient;

        public Builder(EnregistrerClient enregistrerClient)
        {
            this.enregistrerClient = enregistrerClient;
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
        public Register build(Context context)
        {
            return new Register(context, enregistrerClient);
        }
    }
}
