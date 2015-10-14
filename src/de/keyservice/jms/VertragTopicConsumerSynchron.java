package de.keyservice.jms;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Topic;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.AuftragEvent;


@Stateless
public class VertragTopicConsumerSynchron {

    
    @Resource(mappedName = "java:/jms/topic/VertragTopic")
    Topic VertragTopic;
    @Inject
    JMSContext context;

    Set<Auftrag> auftraege = new HashSet<Auftrag>();
    
    @Asynchronous
    public void onReceiveNewAuftrag(@Observes AuftragEvent pAuftragEvent) {
    	auftraege.add(pAuftragEvent.getAuftrag());
    }
    
    
    public Set<Auftrag> getAuftraege() {
        return auftraege;
    }


    public void setAuftraege(Set<Auftrag> auftraege) {
        this.auftraege = auftraege;
    }


    public Auftrag receiveMessage() {
	Auftrag auftrag = new Auftrag();
	try {
//	    auftrag = context.createDurableConsumer(VertragTopic, "UserService").receive().getBody(Auftrag.class);
	    auftrag = context.createConsumer(VertragTopic).receive().getBody(Auftrag.class);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
	return auftrag;
    }
}
