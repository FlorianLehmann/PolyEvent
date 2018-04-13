package fr.unice.polytech.isa.polyevent.entities;

import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

import java.util.ArrayList;
import java.util.List;

public class Prestataire {

    private Mail mail;
    private TypeService typeService;
    private List<Prestation> prestations;

    public Prestataire(){};

    public Prestataire(Mail mail, TypeService typeService){
        this.mail = mail;
        this.typeService = typeService;
        prestations = new ArrayList<>();
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public TypeService getTypeService() {
        return typeService;
    }
}
