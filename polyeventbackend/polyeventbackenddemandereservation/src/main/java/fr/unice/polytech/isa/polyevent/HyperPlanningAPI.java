package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.Salle;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;


public class HyperPlanningAPI {
    private String url;

    public HyperPlanningAPI() {
        this.url = "localhost:9090/salle";
    }

    public boolean reserverSalle(Reservation reservation, Salle salle){

        boolean succes = true;

        JSONObject requete = new JSONObject().put("dateDebut", reservation.getDateDebut()).put("dateFin", reservation.getDateFin())
                .put("emplacement", salle.getNom()).put("nomEvenement", reservation.getEvenement().getNom());

        try {
           int status =  WebClient.create(url).post(requete).getStatus();
            System.out.println(status);
           if(status !=200){
               succes = false;
           }
        } catch (Exception e) {
            System.out.println(e);
            succes = false;
        }

        return succes;
    }
}
