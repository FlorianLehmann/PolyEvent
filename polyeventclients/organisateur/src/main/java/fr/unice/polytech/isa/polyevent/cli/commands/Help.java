package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Help implements Command
{
    private static final Identifier IDENTIFIER = Identifier.HELP;
    private static final String SHORTCUT = "?";
    private static final String FULL_IDENTIFIER = String.format("%s or %s", IDENTIFIER.keyword, SHORTCUT);
    private final Shell shell;
    private final PrintStream out;
    private CommandBuilder builder;

    private Help(Shell shell, PrintStream out)
    {
        this.shell = shell;
        this.out = out;
    }

    @Override
    public void load(List<String> args)
    {
        if (args.size() == 1)
        {
            String keyword = args.get(0);
            builder = shell.findBuilder(keyword);
        }
    }

    @Override
    public void execute()
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

        public Builder(Shell shell)
        {
            this.shell = shell;
        }

        @Override
        public String identifier()
        {
            return FULL_IDENTIFIER;
        }

        @Override
        public boolean match(String keyword)
        {
            return IDENTIFIER.keyword.equals(keyword) || SHORTCUT.equals(keyword);
        }

        @Override
        public String describe()
        {
            return IDENTIFIER.description;
        }

        @Override
        public Help build(Scanner scanner, PrintStream out, boolean echo)
        {
            return new Help(shell, out);
        }
    }
}
