package fr.unice.polytech.isa.polyevent.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Organisateur {

    private String mail;

    public Organisateur() {
    }

    public Organisateur(String mail) {
        this.mail = mail;
    }
}
