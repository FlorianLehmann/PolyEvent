package fr.unice.polytech.isa.polyevent.cli.commands;

public enum Identifier
{
    HELP("help", "Print the help"),
    BYE("bye", "Exit Poly'Event"),
    SUBMIT_EVENT("event", "Submit an event to the system"),
    ADD_RESERVATION("reservation", "Add a reservation to the event"),
    VALIDATE("validate", "validate the event submission"),
    CANCEL("cancel", "Exit the reservation shell and cancel the current event creation"),
    LIST_EVENT("list", "List the event of the user");

    public final String keyword;
    public final String description;

    Identifier(String keyword, String description)
    {
        this.keyword = keyword;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return keyword;
    }
}
