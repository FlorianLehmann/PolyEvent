package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;

import java.io.PrintStream;
import java.util.Scanner;

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
        public Bye build(Scanner scanner, PrintStream out, boolean echo)
        {
            return new Bye(out);
        }
    }
}
