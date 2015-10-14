package de.keyservice.boundary;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.AfterCompletion;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.inject.Named;

import com.sun.xml.internal.bind.XmlAccessorFactory;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.AuftragEvent;
import de.keyservice.jms.VertragTopicConsumerSynchron;
import java.io.Serializable;

@Stateful
@Named("dienstleisterservice")
@SessionScoped // ohne sessionscoped läuft fire & observe
public class DienstleisterService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    VertragTopicConsumerSynchron vertragTopicConsumerSynchron;

    private static Set<Auftrag> auftraege = new HashSet<Auftrag>();

    public void init() {
    }

    public void receiveMessages() {
	auftraege = vertragTopicConsumerSynchron.getAuftraege();
    }

    public void onReceiveNewAuftrag(@Observes AuftragEvent pAuftragEvent) {
	Auftrag lAuftrag = pAuftragEvent.getAuftrag();
	try {
	    auftraege.add((Auftrag)lAuftrag.clone());
	} catch (CloneNotSupportedException e) {
	    e.printStackTrace();
	}
    }
    

    public Set<Auftrag> getAuftraege() {
	return auftraege;
    }

    public void setAuftraege(Set<Auftrag> auftraege) {
	DienstleisterService.auftraege = auftraege;
    }

}
