package de.keyservice.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

import de.keyservice.entity.Auftrag;

@Stateless
public class VertragTopicSender {

    @Inject
    JMSContext context;

    @Resource(mappedName = "java:/jms/topic/VertragTopic")
    Destination VertragTopic;

    public void sendMessage(String message) {
	context.createProducer().setProperty("AuftragID", 1).send(VertragTopic, message);
	context.createProducer().setProperty("AuftragID", 8).send(VertragTopic, "wie geht es dir?");
    }
    
    public void sendAuftrag(Auftrag pAuftrag){
	context.createProducer().send(VertragTopic, pAuftrag);
    }
}
