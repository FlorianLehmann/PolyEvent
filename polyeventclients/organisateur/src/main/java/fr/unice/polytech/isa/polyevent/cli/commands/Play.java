package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Play implements Command
{
    private static final Identifier IDENTIFIER = Identifier.PLAY;
    private final Shell shell;
    private final PrintStream out;
    private String filename;

    Play(Shell shell, PrintStream out)
    {
        this.shell = shell;
        this.out = out;
    }

    @Override
    public void load(List<String> args)
    {
        if (args.size() != 1)
        {
            throw new IllegalArgumentException(String.format("%s expects 1 argument: %s FILENAME", IDENTIFIER, IDENTIFIER));
        }
        filename = args.get(0);
    }

    @Override
    public void execute() throws Exception
    {
        InputStream in = new FileInputStream(new File(filename));
        shell.run(new Scanner(in), out, true);
    }

    public static class Builder implements CommandBuilder<Play>
    {
        private final Shell shell;

        public Builder(Shell shell)
        {
            this.shell = shell;
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
        public Play build(Scanner scanner, PrintStream out, boolean echo)
        {
            return new Play(shell, out);
        }
    }
}
