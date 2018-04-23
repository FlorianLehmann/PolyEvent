package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ManagedBean
@SessionScoped
public class EvenementBean implements Serializable {

    @EJB
    private transient ObtenirProfilOrganisateur profilOrganisateur;
    @ManagedProperty("#{organisateurBean.organisateur}")
    private Organisateur organisateur;
    private List<Evenement> evenements;


    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    @PostConstruct private void loadOrganisateurEtEvenement() {
        evenements = profilOrganisateur.obtenirEvenementOrganisateur(organisateur);
    }
}
