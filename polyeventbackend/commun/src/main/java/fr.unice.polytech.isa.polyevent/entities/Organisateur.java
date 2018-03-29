package fr.unice.polytech.isa.polyevent.entities;

import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @XmlTransient
    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisateur that = (Organisateur) o;
        return mail.getMail().equals(that.mail.getMail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(mail, evenements);
    }
}
