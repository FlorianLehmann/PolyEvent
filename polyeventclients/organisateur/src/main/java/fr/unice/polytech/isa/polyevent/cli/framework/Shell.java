package fr.unice.polytech.isa.polyevent.cli.framework;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        this.builders.addAll(Arrays.asList(builders));
    }

    public void run(Context context)
    {
        boolean shouldContinue = true;

        while (shouldContinue)
        {
            context.out.format(String.format("%%%ds ", 3 * indent), ">>>");
            context.out.flush();
            shouldContinue = processScanner(context);
        }
    }

    private boolean processScanner(Context context)
    {
        if (!context.scanner.hasNextLine())
        {
            context.out.println("End of file. Returning to root shell...");
            return false;
        }

        String command = context.scanner.nextLine();

        if (context.echo)
        {
            context.out.println(command);
        }

        Matcher matcher = COMMAND.matcher(command);

        if (!matcher.matches())
        {
            context.out.println("The command is empty");
            return true;
        }
        return processInput(matcher, context);
    }

    private boolean processInput(Matcher matcher, Context context)
    {
        String keyword = parseKeyword(matcher);
        List<String> arguments = parseArguments(matcher);

        try
        {
            return processCommand(context, keyword, arguments);
        }
        catch (IllegalArgumentException e)
        {
            context.out.println(e.getMessage());
        }
        catch (ConnectException e)
        {
            context.out.println("Could not connect to the server. Check your internet connection and retry");
        }
        catch (Exception e)
        {
            context.out.format("Exception caught while processing command:%n%-10s%n", e.getMessage());
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

    private boolean processCommand(Context context, String keyword, List<String> arguments) throws Exception
    {
        CommandBuilder builder = findBuilder(keyword);
        Command command = builder.build(context);
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
