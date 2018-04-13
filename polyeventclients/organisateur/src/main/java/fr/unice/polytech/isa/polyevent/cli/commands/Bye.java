package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;

import java.io.PrintStream;

public class Bye implements Command
{
    private static final Identifier IDENTIFIER = Identifier.BYE;
    private final PrintStream out;

    private Bye(PrintStream out)
    {
        this.out = out;
    }

    @Override
    public void execute()
    {
        out.println("Bye");
    }

    @Override
    public boolean shouldContinue()
    {
        return false;
    }

    public static class Builder implements CommandBuilder<Bye>
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
        public Bye build(Context context)
        {
            return new Bye(context.out);
        }
    }
}
