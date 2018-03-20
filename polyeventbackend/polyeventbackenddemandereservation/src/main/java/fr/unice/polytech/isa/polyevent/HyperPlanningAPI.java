package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Reservation;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;


public class HyperPlanningAPI {
    private String url;

    public HyperPlanningAPI() {
        this.url = "localhost:9090/salle";
    }

    public void reserverSalle(Reservation reservation){
        JSONObject requete = new JSONObject().put("dateDebut", reservation.getDateDebut()).put("dateFin", reservation.getDateFin())
                .put("emplacement", reservation.getSalle().getNom()).put("nomEvenement", reservation.getEvenement().getNom());

        String str = WebClient.create(url).post(requete).toString();
    }
}
