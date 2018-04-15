package fr.unice.polytech.isa.polyevent.entities;

import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Organisateur implements Serializable {


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private Mail mail;

    @OneToMany(mappedBy = "organisateur")
    private List<Evenement> evenements = new ArrayList<>();

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

        return Objects.hash(mail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
