package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.Salle;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;


public class HyperPlanningAPI {
    private String url;

    public HyperPlanningAPI() {
        this.url = "http://localhost:9090/salle";
    }

    public boolean reserverSalle(Reservation reservation, Salle salle){

        boolean succes = true;

        JSONObject requete = new JSONObject().put("dateDebut", reservation.getDateDebut()).put("dateFin", reservation.getDateFin())
                .put("emplacement", salle.getNom()).put("nomEvenement", reservation.getEvenement().getNom());

//        String str = WebClient.create(url).path("/mailbox")
//                .accept(MediaType.APPLICATION_JSON_TYPE).header("Content-Type", MediaType.APPLICATION_JSON)
//                .post(request.toString(), String.class);

        try {
           int status =  WebClient.create(url).accept(MediaType.APPLICATION_JSON_TYPE)
                   .header("Content-Type", MediaType.APPLICATION_JSON).post(requete.toString()).getStatus();
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
