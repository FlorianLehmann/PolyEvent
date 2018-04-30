package fr.unice.polytech.isa.polyevent.comptable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.isa.polyevent.entities.DemandeEnvoieFacture;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ServeurComptabilite implements WebServiceComptabilite {
    private static final Logger log = Logger.getLogger(ServeurComptabilite.class.getName());
    @Resource(name = "FactureMoM")
    private Queue printerQueue;
    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "FactureMoMAck")
    private Queue factureQueueACK;

    @Override
    public void DemanderFactureServeurComptabilite(DemandeEnvoieFacture demandeEnvoieFacture) {
        if (demandeEnvoieFacture.getCptEssai() < 3) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String s = gson.toJson(demandeEnvoieFacture);
            JSONObject requete = new JSONObject()
                    .put("facture", s);

            try {
                int status = WebClient.create("http://localhost:9095/facture").accept(MediaType.APPLICATION_JSON_TYPE)
                        .header("Content-Type", MediaType.APPLICATION_JSON).post(requete.toString()).getStatus();
                if (status != 200) {
                    reenvoyerFacture(demandeEnvoieFacture);
                    return;
                }
            } catch (Exception e) {
                reenvoyerFacture(demandeEnvoieFacture);
                return;
            }
            try {
                envoyerFactureACK(demandeEnvoieFacture,"Succes");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                envoyerFactureACK(demandeEnvoieFacture, "Echec");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    private void reenvoyerFacture(DemandeEnvoieFacture demandeEnvoieFacture) {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer facturation = session.createProducer(printerQueue);
            facturation.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            demandeEnvoieFacture.setCptEssai(demandeEnvoieFacture.getCptEssai() + 1);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String s = gson.toJson(demandeEnvoieFacture);
            facturation.send(session.createObjectMessage(s));
            facturation.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            log.log(Level.WARNING, "Impossible d'envoyer la facture pour l'evement" + demandeEnvoieFacture.getEvenement().getNom()
                    + "pour l'organisateur " + demandeEnvoieFacture.getOrganisateur().getMail(), e);
        }
    }

    private void envoyerFactureACK(DemandeEnvoieFacture demandeEnvoieFacture, String message) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer facturation = session.createProducer(factureQueueACK);
            facturation.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            String data = "Status de la demande de facturation pour l'evenement : "
                    + demandeEnvoieFacture.getEvenement().getNom()
                    + " de l'organisateur :"
                    + demandeEnvoieFacture.getOrganisateur().getMail()
                    + " Status : " + message;
            facturation.send(session.createObjectMessage(data));
            facturation.close();
            session.close();
            connection.close();
        } finally {
            if (session != null)
                session.close();
            if (connection != null)
                connection.close();
        }
    }
}
