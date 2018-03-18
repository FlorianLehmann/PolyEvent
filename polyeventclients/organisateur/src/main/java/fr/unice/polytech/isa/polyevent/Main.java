package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.api.PolyEventAPI;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.*;

import java.io.PrintStream;
import java.util.logging.Logger;

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
            runCLI(ns, System.out);
        }
        catch (ArgumentParserException e)
        {
            parser.handleError(e);
        }
    }

    private static void runCLI(Namespace namespace, PrintStream out)
    {
        String host = namespace.getString("hostname");
        String port = namespace.getString("port");

        out.println("Starting Poly'Event by Team H");
        out.println("   - Remote server: " + host);
        out.println("   - Port number: " + port);

        PolyEventAPI polyEventAPI = new PolyEventAPI(host, port);
        polyEventAPI.run();
        out.println("Exiting Poly'Event by Team H\n\n");
    }
}
