package fr.unice.polytech.isa.polyevent.cli.framework;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell
{
    private static final Pattern COMMAND = Pattern.compile("^\\s*(\\S+)((?:\\s+[\\S]+)*)\\s*$");
    private static final Pattern ARGUMENTS = Pattern.compile("\"([^\"]*)\"|(\\S+)");
    private final List<CommandBuilder> builders = new ArrayList<>();
    private final int indent;

    public Shell()
    {
        this(1);
    }

    private Shell(int indent)
    {
        this.indent = indent;
    }

    public void register(CommandBuilder... builders)
    {
        this.register(Arrays.asList(builders));
    }

    public void register(List<CommandBuilder> builders)
    {
        this.builders.addAll(builders);
    }

    public void run(Scanner scanner, PrintStream out)
    {
        this.run(scanner, out, false);
    }

    public void run(Scanner scanner, PrintStream out, boolean echo)
    {
        boolean shouldContinue = true;

        while (shouldContinue)
        {
            out.format(String.format("%%%ds ", 3 * indent), ">>>");
            out.flush();
            shouldContinue = processScanner(scanner, out, echo);
        }
    }

    private boolean processScanner(Scanner scanner, PrintStream out, boolean echo)
    {
        if (!scanner.hasNextLine())
        {
            out.println("End of file. Returning to root shell...");
            return false;
        }

        String command = scanner.nextLine();

        if (echo)
        {
            out.println(command);
        }

        Matcher matcher = COMMAND.matcher(command);

        if (!matcher.matches())
        {
            out.println("The command is empty");
            return true;
        }
        return processInput(matcher, scanner, out, echo);
    }

    private boolean processInput(Matcher matcher, Scanner scanner, PrintStream out, boolean echo)
    {
        String keyword = parseKeyword(matcher);
        List<String> arguments = parseArguments(matcher);

        try
        {
            return processCommand(scanner, out, keyword, arguments, echo);
        }
        catch (IllegalArgumentException e)
        {
            out.println(e.getMessage());
        }
        catch (Exception e)
        {
            out.format("Exception caught while processing command:%n%-10s%n", e.getMessage());
        }
        return true;
    }

    private String parseKeyword(Matcher matcher)
    {
        return matcher.group(1);
    }

    private List<String> parseArguments(Matcher matcher)
    {
        Matcher args = ARGUMENTS.matcher(matcher.group(2));
        List<String> arguments = new ArrayList<>();
        while (args.find())
        {
            arguments.add(args.group(1) != null ? args.group(1) : args.group(2));
        }
        return arguments;
    }

    public CommandBuilder findBuilder(String keyword)
    {
        return builders.stream()
                .filter(builder -> builder.match(keyword))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown Command"));
    }

    private boolean processCommand(Scanner scanner, PrintStream out, String keyword, List<String> arguments, boolean echo) throws Exception
    {
        CommandBuilder builder = findBuilder(keyword);
        Command command = builder.build(scanner, out, echo);
        return command.process(arguments);
    }

    public List<CommandBuilder> getBuilders()
    {
        return new ArrayList<>(builders);
    }

    public Shell subShell()
    {
        return new Shell(indent + 1);
    }
}
