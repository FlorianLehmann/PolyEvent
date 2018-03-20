package fr.unice.polytech.isa.polyevent.cli.framework;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell
{
    private static final Pattern PATTERN = Pattern.compile("^\\s*(\\S+)((?:\\s+[\\S]+)*)\\s*$");
    private final List<CommandBuilder> builders = new ArrayList<>();

    public void register(CommandBuilder... builders)
    {
        this.builders.addAll(Arrays.asList(builders));
    }

    public void run(InputStream in, PrintStream out)
    {
        Scanner scanner = new Scanner(in);
        boolean shouldContinue = true;

        while (shouldContinue && scanner.hasNextLine())
        {
            String command = scanner.nextLine();
            Matcher matcher = PATTERN.matcher(command);
            if (!matcher.matches())
            {
                out.println("The command is empty");
                continue;
            }

            String keyword = matcher.group(1);
            String[] arguments = matcher.group(2).trim().split("\\s+");
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
                out.format("Exception caught while processing command:%n%10s", e.getMessage());
            }
        }
    }

    private CommandBuilder findBuilder(String keyword)
    {
        return builders.stream()
                .filter(builder -> builder.match(keyword))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown Command"));
    }

    private boolean processCommand(String keyword, String[] arguments) throws Exception
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
