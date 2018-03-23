package fr.unice.polytech.isa.polyevent.entities;

import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

import java.util.ArrayList;
import java.util.List;

public class Organisateur {

    private Mail mail;
    private List<Evenement> evenements = new ArrayList<>();;

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

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }
}
