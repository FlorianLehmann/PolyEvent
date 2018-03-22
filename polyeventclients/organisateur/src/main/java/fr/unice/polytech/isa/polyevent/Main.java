package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.api.PolyEventAPI;
import fr.unice.polytech.isa.polyevent.cli.commands.Bye;
import fr.unice.polytech.isa.polyevent.cli.commands.Help;
import fr.unice.polytech.isa.polyevent.cli.commands.SubmitEvent;
import fr.unice.polytech.isa.polyevent.cli.framework.Shell;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.InputStream;
import java.io.PrintStream;

public class Main
{
    private Main()
    {
    }

    public static void main(String[] args)
    {
        ArgumentParser parser = ArgumentParsers.newArgumentParser("cli");
        parser.addArgument("--hostname").setDefault("localhost").help("Host name. Default is localhost");
        parser.addArgument("--port").setDefault("8080").help("Port number. Default is 8080");

        try
        {
            Namespace ns = parser.parseArgs(args);
            runCLI(ns, System.in, System.out);
        }
        catch (ArgumentParserException e)
        {
            parser.handleError(e);
        }
    }

    private static void runCLI(Namespace namespace, InputStream in, PrintStream out)
    {
        String host = namespace.getString("hostname");
        String port = namespace.getString("port");

        out.println("Starting Poly'Event by Team H");
        out.println("   - Remote server: " + host);
        out.println("   - Port number: " + port);

        PolyEventAPI api = new PolyEventAPI(host, port);
        Shell shell = new Shell();
        shell.register(
                new Help.Builder(shell, out),
                new Bye.Builder(out),
                new SubmitEvent.Builder(in, out, api.demandeEvenement)
        );

        out.println("Submit your event. Type ? for help.");
        shell.run(in, out);
        out.println("Exiting Poly'Event by Team H");
    }
}
