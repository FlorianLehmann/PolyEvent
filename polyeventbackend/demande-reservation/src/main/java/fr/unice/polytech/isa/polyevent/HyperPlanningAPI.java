package fr.unice.polytech.isa.polyevent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.unice.polytech.isa.polyevent.entities.Reservation;
import fr.unice.polytech.isa.polyevent.entities.Salle;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;


public class HyperPlanningAPI {
    private String url;

    public HyperPlanningAPI() {
        this.url = "http://localhost:9090/salle";
    }

    public String reserverSalle(Reservation reservation, Salle salle){

        String reponse;

        JSONObject requete = new JSONObject().put("dateDebut", reservation.getDateDebut()).put("dateFin", reservation.getDateFin())
                .put("emplacement", salle.getNom()).put("nomEvenement", reservation.getEvenement().getNom());

        try {
           int status =  WebClient.create("http://localhost:9090/salle").accept(MediaType.APPLICATION_JSON_TYPE)
                   .header("Content-Type", MediaType.APPLICATION_JSON).post(requete.toString()).getStatus();
           if(status !=200){
               reponse = "Erreur lors de la réservation de la salle";
               return reponse;
           }
        } catch (Exception e) {
            reponse = "La connexion à hyperplanning a échoué";
            return reponse;
        }

        reponse = "Succès";
        return reponse;
    }

    public List<String> DemandeSallesDisponible(List<Reservation> reservations) throws Exception {

        List<String> reponse = new LinkedList<>();
        JSONArray requete = new JSONArray();

        for (Reservation reservation : reservations) {
            JSONObject object = new JSONObject().put("dateDebut",reservation.getDateDebut()).put("dateFin", reservation.getDateFin())
                    .put("typeSalle",reservation.getTypeSalle().toString());
            requete.put(object);
        }
        try {
             Response reponseServeur = WebClient.create("http://localhost:9090/getsalle").accept(MediaType.APPLICATION_JSON_TYPE)
                    .header("Content-Type", MediaType.APPLICATION_JSON).post(requete.toString());
             reponse = (LinkedList<String>) new Gson().fromJson(reponseServeur.readEntity(String.class).toString(),
                    new TypeToken<LinkedList<String>>() {}.getType());
            if(reponseServeur.getStatus() !=200){
                throw new Exception("Erreur lors de la demande des salles disponible");
            }
        } catch (Exception e) {
            throw new Exception("La connexion à hyperplanning a échoué");
        }
        return reponse;
    }
}
