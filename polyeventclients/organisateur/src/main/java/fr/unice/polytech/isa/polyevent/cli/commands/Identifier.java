package fr.unice.polytech.isa.polyevent.cli.commands;

public enum Identifier
{
    HELP("help", "Print the help (help or ?)"),
    BYE("bye", "Exit Poly'Event (bye)"),
    SUBMIT_EVENT("event", "Submit an event to the system (? event for more info)"),
    ADD_RESERVATION("reservation", "Add a reservation to the event (? reservation for more info)"),
    VALIDATE("validate", "validate the event submission (validate)"),
    CANCEL("cancel", "Exit the reservation shell and cancel the current event creation (cancel)"),
    LIST_EVENT("list", "List the event of the user (list MAIL)"),
    PAY("pay", "Pay an event (? pay for more info"),
    PLAY("play", "Play commands stored in given file (play FILENAME)");

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
