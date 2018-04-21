package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Organisateur;
import fr.unice.polytech.isa.polyevent.entities.outils.Signal;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@SessionScoped
public class OrganisateurBean implements Serializable {

    private String mail;
    private Organisateur organisateur;

    @EJB
    private transient TrouverOrganisateur trouverOrganisateur;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    // Invoked when the "Select" button is pushed
    public String select() {
        Optional<Organisateur> optional = trouverOrganisateur.connexion(mail);
        if (optional.isPresent()) {
            organisateur = optional.get();
            return Signal.SELECTIONNE;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Client inconnu: " + getMail()));
            return Signal.INCONNU;
        }
    }
}
