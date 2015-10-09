package de.keyservice.jms;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;

import de.keyservice.entity.Auftrag;

@Stateful
public class VertragTopicConsumerSynchron {

    
    @Resource(mappedName = "java:/jms/topic/VertragTopic")
    Destination VertragTopic;
    @Inject
    JMSContext context;

    public Auftrag receiveMessage() {
	Auftrag auftrag = new Auftrag();
	try {
	    auftrag = context.createConsumer(VertragTopic).receive().getBody(Auftrag.class);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
	return auftrag;
    }
}
