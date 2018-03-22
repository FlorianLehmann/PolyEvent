package fr.unice.polytech.isa.polyevent.entities;

import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

public class Organisateur {

    private Mail mail;

    public Organisateur() {
    }

    public Organisateur(String mail) {
        this.mail = new Mail(mail);
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
