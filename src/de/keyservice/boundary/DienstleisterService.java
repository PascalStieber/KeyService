package de.keyservice.boundary;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.entity.Auftrag;
import de.keyservice.jms.VertragTopicConsumerSynchron;

@Stateful
@SessionScoped
@Named("dienstleisterservice")
public class DienstleisterService {
    
    @Inject
    VertragTopicConsumerSynchron vertragTopicConsumerSynchron;

    private Set<Auftrag> auftraege = new HashSet<Auftrag>();
    
    public void init(){
//	Synchron
//	auftraege.add(vertragTopicConsumerSynchron.receiveMessage());
	
    }
    
    public void onAktionDeleted(@Observes Auftrag pAuftrag) {
	auftraege.add(pAuftrag);
	System.out.println("!!!!!auftrag wurde gefired");
    }
    
    public Set<Auftrag> getAuftraege() {
	return auftraege;
    }

    public void setAuftraege(Set<Auftrag> auftraege) {
	this.auftraege = auftraege;
    }

    
    
    
}
