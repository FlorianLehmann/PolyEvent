package fr.unice.polytech.isa.polyevent.cli.framework;

import java.io.PrintStream;
import java.util.Scanner;

public interface CommandBuilder<T extends Command>
{
    String identifier();

    default boolean match(String keyword)
    {
        return identifier().equals(keyword);
    }

    String describe();

    default String help()
    {
        return describe();
    }

    T build(Scanner scanner, PrintStream out, boolean echo);
}
