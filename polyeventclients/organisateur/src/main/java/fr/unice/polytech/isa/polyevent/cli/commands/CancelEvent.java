package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;

public class CancelEvent implements Command
{
    private static final String IDENTIFIER = "cancel";

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
            return IDENTIFIER;
        }

        @Override
        public String describe()
        {
            return "Exit the reservation shell and cancel the current event creation";
        }

        @Override
        public Command build()
        {
            return new CancelEvent();
        }
    }
}
