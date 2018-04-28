package fr.unice.polytech.isa.polyevent.entities;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Objects;

public class DemandeEnvoieFacture implements Serializable {
    @Expose
    private Organisateur organisateur;
    @Expose
    private Evenement evenement;
    @Expose
    private int cptEssai = 0;

    public DemandeEnvoieFacture(Organisateur organisateur, Evenement evenement) {
        this.organisateur = organisateur;
        this.evenement = evenement;
    }

    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public int getCptEssai() {
        return cptEssai;
    }

    public void setCptEssai(int cptEssai) {
        this.cptEssai = cptEssai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandeEnvoieFacture that = (DemandeEnvoieFacture) o;
        return cptEssai == that.cptEssai &&
                Objects.equals(organisateur, that.organisateur) &&
                Objects.equals(evenement, that.evenement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(organisateur, evenement, cptEssai);
    }
}
