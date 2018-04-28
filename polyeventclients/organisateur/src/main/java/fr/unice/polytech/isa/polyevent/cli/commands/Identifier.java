package fr.unice.polytech.isa.polyevent.cli.commands;

public enum Identifier
{
    HELP("help", "Print the help (help or ?)"),
    BYE("bye", "Exit Poly'Event (bye)"),
    LOGIN("login", "Login into your account (login MAIL)"),
    LOGOUT("logout", "Logout from your account (logout)"),
    REGISTER("register", "Register a new account (register MAIL)"),
    SUBMIT_EVENT("event", "Submit an event to the system (? event for more info)"),
    ADD_RESERVATION("reservation", "Add a reservation to the event (? reservation for more info)"),
    ADD_SERVICE("service", "Add a service to the event (? service for more info)"),
    VALIDATE("validate", "validate the event submission (validate)"),
    CANCEL("cancel", "Exit the reservation shell and cancel the current event creation (cancel)"),
    LIST_EVENT("list", "List your events (list)"),
    INVOICE("invoice", "Invoice an event (? invoice for more info)"),
    PAY("pay", "Pay an event (? pay for more info)"),
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
