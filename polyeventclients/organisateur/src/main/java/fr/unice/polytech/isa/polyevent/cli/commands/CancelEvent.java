package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;

import java.io.PrintStream;
import java.util.Scanner;

public class CancelEvent implements Command
{
    private static final Identifier IDENTIFIER = Identifier.CANCEL;

    @Override
    public void execute() throws Exception
    {

    }

    @Override
    public boolean shouldContinue()
    {
        return false;
    }

    public static class Builder implements CommandBuilder
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
        public Command build(Scanner scanner, PrintStream out, boolean echo)
        {
            return new CancelEvent();
        }
    }
}
