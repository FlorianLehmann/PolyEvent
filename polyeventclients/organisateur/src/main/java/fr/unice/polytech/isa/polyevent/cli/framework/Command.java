package fr.unice.polytech.isa.polyevent.cli.framework;

public interface Command
{
    void load(String... args) throws Exception;
    void execute() throws Exception;
    boolean shouldContinue();

    default boolean process(String... args) throws Exception
    {
        load(args);
        execute();
        return shouldContinue();
    }
}
