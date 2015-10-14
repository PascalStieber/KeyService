package de.keyservice.boundary;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.AuftragEvent;
import de.keyservice.jms.VertragTopicConsumerSynchron;

@Stateful
@Named("dienstleisterservice")
@SessionScoped // ohne sessionscoped läuft fire & observe
public class DienstleisterService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    VertragTopicConsumerSynchron vertragTopicConsumerSynchron;

    private static Set<Auftrag> auftraege = new HashSet<Auftrag>();
    private Auftrag selectedAuftrag;
    
    public void init() {
    }
    
    public String erstelleAngebot(Auftrag pAuftrag){
	this.setSelectedAuftrag(pAuftrag);
	return "/faces/service/newAngebot.xhtml?faces-redirect=true&auftragid="+pAuftrag.getId();
    }

//    public void receiveMessages() {
//	auftraege = vertragTopicConsumerSynchron.getAuftraege();
//    }

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

    public Auftrag getSelectedAuftrag() {
	return selectedAuftrag;
    }

    public void setSelectedAuftrag(Auftrag selectedAuftrag) {
	this.selectedAuftrag = selectedAuftrag;
    }

}
