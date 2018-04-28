package fr.unice.polytech.isa.polyevent.comptable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.isa.polyevent.entities.DemandeEnvoieFacture;
import org.apache.openejb.util.LogCategory;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "FactureMoM", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class FactureMoM implements MessageListener {

    private static final org.apache.openejb.util.Logger log =
            org.apache.openejb.util.Logger.getInstance(LogCategory.ACTIVEMQ, FactureMoM.class);

    @Resource
    private MessageDrivenContext context;

    @EJB
    private WebServiceComptabilite webServiceComptabilite;

    /**
     * Message-based reception (automatically handled by the container)
     *
     * @param message a JMS message that contains an Order
     * @throws RuntimeException
     */
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = ((ObjectMessage) message);
            objectMessage.getObject();
            String s = (String) ((ObjectMessage) message).getObject();
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            DemandeEnvoieFacture data = gson.fromJson(s, DemandeEnvoieFacture.class);
            handle(data);
        } catch (JMSException e) {
            log.error("Java message service exception while handling " + message);
            log.error(e.getMessage(), e);
            context.setRollbackOnly();
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Business logic to process an Order (basically sysout)
     *
     * @param data
     */
    private void handle(DemandeEnvoieFacture data) {
        data.setEvenement(entityManager.merge(data.getEvenement()));
        data.setOrganisateur(entityManager.merge(data.getOrganisateur()));

        log.info("FactureMoM:\n tentative #" + data.getCptEssai() + "\nFacturation demander pour #" + data.getOrganisateur().getMail());
        //Thread.sleep(4000); // it takes time ... 4 seconds actually
        webServiceComptabilite.DemanderFactureServeurComptabilite(data);

    }
}

