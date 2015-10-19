package de.keyservice.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AuftragController;
import de.keyservice.entity.Auftrag;
import de.keyservice.jms.VertragTopicConsumerSynchron;

@Stateful
@Named("dienstleisterservice")
@SessionScoped // ohne sessionscoped läuft fire & observe
public class DienstleisterService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    VertragTopicConsumerSynchron vertragTopicConsumerSynchron;
    @Resource
    SessionContext sessionContext;
    @Inject
    AuftragController auftragControl;

    private List<Auftrag> auftraege = new ArrayList<Auftrag>();
    private Auftrag selectedAuftrag;
    private String loggedInUser;

    @PostConstruct
    public void init() {
	loggedInUser = sessionContext.getCallerPrincipal().getName();
    }

    public void onLoad(){
	auftraege = auftragControl.findAllAuftraege();
    }
    
    public String erstelleAngebot(Auftrag pAuftrag) {
	this.setSelectedAuftrag(pAuftrag);
	return "/faces/service/newAngebot.xhtml?faces-redirect=true&auftragid=" + pAuftrag.getId();
    }



    public String getLoggedInUser() {
	return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

    public List<Auftrag> getAuftraege() {
	return auftraege;
    }

    public void setAuftraege(List<Auftrag> auftraege) {
	this.auftraege = auftraege;
    }

    public Auftrag getSelectedAuftrag() {
	return selectedAuftrag;
    }

    public void setSelectedAuftrag(Auftrag selectedAuftrag) {
	this.selectedAuftrag = selectedAuftrag;
    }

}
