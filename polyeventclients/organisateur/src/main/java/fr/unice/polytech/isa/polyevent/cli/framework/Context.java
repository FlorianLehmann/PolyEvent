package fr.unice.polytech.isa.polyevent.cli.framework;

import java.io.PrintStream;
import java.util.Scanner;

public class Context
{
    public final Scanner scanner;
    public final PrintStream out;
    public final boolean echo;

    public Context(Scanner scanner, PrintStream out, boolean echo)
    {
        this.scanner = scanner;
        this.out = out;
        this.echo = echo;
    }
}
