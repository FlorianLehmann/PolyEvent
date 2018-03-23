package fr.unice.polytech.isa.polyevent.utils;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.Salle;
import fr.unice.polytech.isa.polyevent.entities.TypeSalle;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class Database {

    private List<Evenement> evenements = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Salle> salles = Arrays.asList(new Salle(TypeSalle.SALLE, new ArrayList<>(), "O+310"),
            new Salle(TypeSalle.SALLE, new ArrayList<>(), "O+311"),new Salle(TypeSalle.AMPHI, new ArrayList<>(), "O+228"));

    public List<Salle> getSalles() { return salles; }
    public List<Reservation> getReservations() { return reservations; }
    public List<Evenement> getEvenements() { return evenements; }

    public void flush() {
        evenements = new ArrayList<>();
        reservations = new ArrayList<>();
        salles = Arrays.asList(new Salle(TypeSalle.SALLE, new ArrayList<>(), "O+310"),
                new Salle(TypeSalle.SALLE, new ArrayList<>(), "O+311"),new Salle(TypeSalle.AMPHI, new ArrayList<>(), "O+228"));
    }

}
