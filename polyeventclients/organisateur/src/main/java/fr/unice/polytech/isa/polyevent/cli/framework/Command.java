package fr.unice.polytech.isa.polyevent.cli.framework;

import java.util.List;

public interface Command
{
    default void load(List<String> args) throws Exception
    {

    }

    void execute() throws Exception;

    default boolean shouldContinue()
    {
        return true;
    }

    default boolean process(List<String> args) throws Exception
    {
        load(args);
        execute();
        return shouldContinue();
    }
}
