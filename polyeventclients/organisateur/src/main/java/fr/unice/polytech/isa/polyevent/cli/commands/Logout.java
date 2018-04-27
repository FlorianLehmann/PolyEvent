package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;

import java.io.PrintStream;

public class Logout implements Command
{
    private static final Identifier IDENTIFIER = Identifier.LOGOUT;
    private final PrintStream out;

    private Logout(PrintStream out)
    {
        this.out = out;
    }

    @Override
    public void execute()
    {
        out.println("You are now logged out");
    }

    @Override
    public boolean shouldContinue()
    {
        return false;
    }

    public static class Builder implements CommandBuilder<Logout>
    {
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
        public Logout build(Context context)
        {
            return new Logout(context.out);
        }
    }
}