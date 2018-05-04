package fr.unice.polytech.isa.polyevent.comptable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.isa.polyevent.entities.DemandeEnvoieFacture;
import fr.unice.polytech.isa.polyevent.entities.Evenement;
import fr.unice.polytech.isa.polyevent.entities.Organisateur;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Comptable implements Facturer, EnvoyerFacture {
    private static final Logger log = Logger.getLogger(Comptable.class.getName());
    @Resource(name = "FactureMoM")
    private Queue printerQueue;
    @Resource
    private ConnectionFactory connectionFactory;

    @Override
    public StatusPayement facturer(Evenement evenement) {
        return StatusPayement.PAYEMENT_EFFECTUER;
    }

    @Override
    public void envoyerFacture(Organisateur organisateur, Evenement evenement) {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer facturation = session.createProducer(printerQueue);
            facturation.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            DemandeEnvoieFacture demandeEnvoieFacture = new DemandeEnvoieFacture(organisateur, evenement);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String s = gson.toJson(demandeEnvoieFacture);
            facturation.send(session.createObjectMessage(s));
            facturation.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            log.log(Level.WARNING, "Impossible d'envoyer la facture pour l'evement" + evenement.getNom()
                    + "pour l'organisateur " + organisateur.getMail(), e);
        }
    }
}
