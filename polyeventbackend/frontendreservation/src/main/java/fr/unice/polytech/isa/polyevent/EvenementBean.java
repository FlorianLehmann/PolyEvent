package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.webservice.DemanderEvenement;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@SessionScoped
public class EvenementBean implements Serializable {

    @EJB
    private transient DemanderEvenement demanderEvenement;
    @ManagedProperty("#{organisateurBean.organisateur}")
    private Organisateur organisateur;
    private List<Integer> evenements;

    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    public List<Integer> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Integer> evenements) {
        this.evenements = evenements;
    }

    @PostConstruct private void loadOrganisateurEtEvenement() {
        evenements = new LinkedList<>();
        evenements.add(1);
        evenements.add(2);
        evenements.add(3);
    }
}
