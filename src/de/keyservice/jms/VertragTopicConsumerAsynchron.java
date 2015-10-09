package de.keyservice.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import de.keyservice.entity.Auftrag;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/VertragTopic"),
//	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")})
//	@ActivationConfigProperty(propertyName = "messageSelector",
	// propertyValue = "AuftragID BETWEEN 0 AND 5")
//	propertyValue = "AuftragID > 5") })
public class VertragTopicConsumerAsynchron implements MessageListener {

    @Inject
    private Event<Auftrag> auftragEvent;
    
    @Override
    public void onMessage(Message message) {
	System.out.println("Message wurde empfangen....");
	try {
	    Auftrag auftrag = new Auftrag();
	    auftrag = message.getBody(Auftrag.class);
	    auftragEvent.fire(auftrag);
	    
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

    
}
