package fr.unice.polytech.isa.polyevent.cli.framework;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
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

    public Shell(int indent)
    {
        this.indent = indent;
    }

    public void register(CommandBuilder... builders)
    {
        this.builders.addAll(Arrays.asList(builders));
    }

    public void run(InputStream in, PrintStream out)
    {
        Scanner scanner = new Scanner(in);
        boolean shouldContinue = true;

        while (shouldContinue)
        {
            out.format(String.format("%%%ds ", 3 * indent), ">>>");
            out.flush();
            if (!scanner.hasNextLine())
            {
                break;
            }

            String command = scanner.nextLine();
            Matcher matcher = COMMAND.matcher(command);

            if (!matcher.matches())
            {
                out.println("The command is empty");
                continue;
            }

            String keyword = parseKeyword(matcher);
            List<String> arguments = parseArguments(matcher);

            try
            {
                shouldContinue = processCommand(keyword, arguments);
            }
            catch (IllegalArgumentException e)
            {
                out.println(e.getMessage());
            }
            catch (Exception e)
            {
                out.format("Exception caught while processing command:%n%-10s%n", e.getMessage());
            }
        }
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
            arguments.add(args.group(1) != null ? args.group(1): args.group(2));
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

    private boolean processCommand(String keyword, List<String> arguments) throws Exception
    {
        CommandBuilder builder = findBuilder(keyword);
        Command command = builder.build();
        return command.process(arguments);
    }

    public List<CommandBuilder> getBuilders()
    {
        return new ArrayList<>(builders);
    }
}
