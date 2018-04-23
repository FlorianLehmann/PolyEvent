package fr.unice.polytech.isa.polyevent.entities.exceptions;

public class ClientPasEnregistreException extends Exception {

    private String mail;

    public ClientPasEnregistreException(){

    }

    public ClientPasEnregistreException(String mail){
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
