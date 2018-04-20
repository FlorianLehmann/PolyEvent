package fr.unice.polytech.isa.polyevent.entities.exceptions;

public class ClientDejaCreeException extends Exception {

    private String mail;

    public ClientDejaCreeException(String mail) {
        this.mail = mail;
    }


    public ClientDejaCreeException() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}