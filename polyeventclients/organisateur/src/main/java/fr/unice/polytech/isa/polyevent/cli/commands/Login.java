package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.api.PolyEventAPI;
import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import fr.unice.polytech.isa.polyevent.stubs.Token;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.List;

public class Login implements Command
{
    private static final Identifier IDENTIFIER = Identifier.LOGIN;
    private final Context context;
    private final Shell shell;
    private final PolyEventAPI api;
    private String mail;

    public Login(Context context, Shell shell, PolyEventAPI api)
    {
        this.context = context;
        this.shell = shell;
        this.api = api;
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
        try
        {
            Token token = api.connecterClient.connexion(mail);
            Shell subShell = this.shell.subShell();
            subShell.register(
                    new Help.Builder(subShell),
                    new SubmitEvent.Builder(token, subShell, api.demandeEvenement),
                    new ListEvent.Builder(token, api.obtenirEvenementOrganisateur),
                    new Pay.Builder(token, api.payerEvenement),
                    new Logout.Builder()
            );
            context.out.format("Welcome %s, you can manage your events here. Type ? for help.%n", mail);
            subShell.run(context);
        }
        catch (SOAPFaultException e)
        {
            context.out.println("This account doesn't exist. Register first with the command (register MAIL)");
        }
    }

    public static class Builder implements CommandBuilder<Login>
    {
        private final Shell shell;
        private final PolyEventAPI api;

        public Builder(Shell shell, PolyEventAPI api)
        {
            this.shell = shell;
            this.api = api;
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
        public Login build(Context context)
        {
            return new Login(context, shell, api);
        }
    }
}
