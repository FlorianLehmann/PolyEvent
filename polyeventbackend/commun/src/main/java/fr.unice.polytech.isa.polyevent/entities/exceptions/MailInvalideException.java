package fr.unice.polytech.isa.polyevent.entities.exceptions;

public class MailInvalideException extends RuntimeException {

    public MailInvalideException() {
        super("L adresse mail n'est pas valide");
    }

}
