package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;

import java.io.PrintStream;
import java.util.List;

public class Help implements Command
{
    private static final String FULL_IDENTIFIER = "help";
    private static final String SHORTCUT = "?";
    private static final String IDENTIFIER = String.format("%s or %s", FULL_IDENTIFIER, SHORTCUT);
    private final Shell shell;
    private final PrintStream out;
    private CommandBuilder builder;

    private Help(Shell shell, PrintStream out)
    {
        this.shell = shell;
        this.out = out;
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() == 1)
        {
            String keyword = args.get(0);
            builder = shell.findBuilder(keyword);
        }
    }

    @Override
    public void execute() throws Exception
    {
        if (builder == null)
        {
            help();
        }
        else
        {
            help(builder);
        }
    }

    private void help()
    {
        out.println("Available commands:");
        for (CommandBuilder commandBuilder : shell.getBuilders())
        {
            out.format("  - %-20s%s%n", commandBuilder.identifier(), commandBuilder.describe());
        }
        out.println("Use \"help COMMAND\" for more details");
    }

    private void help(CommandBuilder builder)
    {
        out.println(builder.help());
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
