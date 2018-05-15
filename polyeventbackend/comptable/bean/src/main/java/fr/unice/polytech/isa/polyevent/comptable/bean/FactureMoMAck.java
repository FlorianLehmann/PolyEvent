package fr.unice.polytech.isa.polyevent.comptable.bean;


import org.apache.openejb.util.LogCategory;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven
public class FactureMoMAck implements MessageListener {

	private static final org.apache.openejb.util.Logger log =
			org.apache.openejb.util.Logger.getInstance(LogCategory.ACTIVEMQ, FactureMoMAck.class);

	@Override
	public void onMessage(Message message) {
		try {
			String data = (String) ((ObjectMessage) message).getObject();
			log.info("\n\n****\n** ACK: " + data + "\n****\n");
		} catch (JMSException e) {
			log.error(e.getMessage(),e);
		}
	}

}
