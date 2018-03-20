package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;

import java.io.PrintStream;

public class Bye implements Command
{
    private static final String IDENTIFIER = "bye";
    private final PrintStream out;

    private Bye(PrintStream out)
    {
        this.out = out;
    }

    @Override
    public void load(String... args)
    {
        // No argument is expected
    }

    @Override
    public void execute() throws Exception
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
        private final PrintStream out;

        public Builder(PrintStream out)
        {
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
            return "Exit Poly'Event";
        }

        @Override
        public Bye build()
        {
            return new Bye(out);
        }
    }
}
