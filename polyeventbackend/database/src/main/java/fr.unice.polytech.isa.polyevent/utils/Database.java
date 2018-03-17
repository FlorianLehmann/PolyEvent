package fr.unice.polytech.isa.polyevent.utils;

import fr.unice.polytech.isa.polyevent.entities.Evenement;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class Database {

    private List<Evenement> evenements = new ArrayList<>();
    public List<Evenement> getEvenements() { return evenements; }

    public void flush() { evenements = new ArrayList<>(); }

}
