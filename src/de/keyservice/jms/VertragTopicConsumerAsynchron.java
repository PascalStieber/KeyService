package de.keyservice.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.security.annotation.SecurityDomain;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.ContractEvent;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/VertragTopic"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") })
@SecurityDomain("ServiceUser")
public class VertragTopicConsumerAsynchron implements MessageListener {

    @Inject
    private Event<ContractEvent> contractEvent;

    @Override
    public void onMessage(Message message) {
	
	try {
	    Auftrag auftrag = new Auftrag();
	    auftrag = message.getBody(Auftrag.class);
	    contractEvent.fire(new ContractEvent(auftrag));
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

}
