package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;

import java.io.PrintStream;

public class Help implements Command
{
    private static final String FULL_IDENTIFIER = "help";
    private static final String SHORTCUT = "?";
    private static final String IDENTIFIER = String.format("%s or %s", FULL_IDENTIFIER, SHORTCUT);
    private final Shell shell;
    private final PrintStream out;

    private Help(Shell shell, PrintStream out)
    {
        this.shell = shell;
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
        out.println("Available commands:");
        for (CommandBuilder commandBuilder : shell.getBuilders())
        {
            out.format("  - %-20s%s%n", commandBuilder.identifier(), commandBuilder.describe());
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return true;
    }

    public static class Builder implements CommandBuilder<Help>
    {
        private final Shell shell;
        private final PrintStream out;

        public Builder(Shell shell, PrintStream out)
        {
            this.shell = shell;
            this.out = out;
        }

        @Override
        public String identifier()
        {
            return IDENTIFIER;
        }

        @Override
        public boolean match(String keyword)
        {
            return FULL_IDENTIFIER.equals(keyword) || SHORTCUT.equals(keyword);
        }

        @Override
        public String describe()
        {
            return "Print the help";
        }

        @Override
        public Help build()
        {
            return new Help(shell, out);
        }
    }
}
