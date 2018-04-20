package fr.unice.polytech.isa.polyevent.utils;

import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.entities.outils.Mail;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class Database {

    private List<Evenement> evenements = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    private List<Organisateur> organisateurs = new ArrayList<>();
    private List<Salle> salles = Arrays.asList(new Salle("O+310"),
            new Salle("O+311"),new Salle("O+228"));
    private List<Prestataire> prestataires = Arrays.asList(new Prestataire(new Mail("café@gmail.com"), new TypeService("café")));
    //private List<Prestataire> prestataires = new ArrayList<>();

    private List<Prestation> prestations = new ArrayList<>();

    public List<Salle> getSalles() { return salles; }
    public List<Reservation> getReservations() { return reservations; }
    public List<Evenement> getEvenements() { return evenements; }

    public List<Prestataire> getPrestataires() {
        return prestataires;
    }

    public List<Organisateur> getOrganisateurs() {
        return organisateurs;
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public void flush() {
        evenements = new ArrayList<>();
        reservations = new ArrayList<>();
        salles = Arrays.asList(new Salle("O+310"),
                new Salle("O+311"),new Salle("O+228"));
        prestataires = new ArrayList<>();
        prestataires.add(new Prestataire(new Mail("café@gmail.com"), new TypeService("café")));
        prestataires.add(new Prestataire(new Mail("bus@gmail.com"), new TypeService("bus")));
        prestations = new ArrayList<>();
        organisateurs = new ArrayList<>();
    }

}
