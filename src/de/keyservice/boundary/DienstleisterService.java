package de.keyservice.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
import de.keyservice.controller.PersonController;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.Person;
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
    @Inject
    PersonController personControl;

    private List<Auftrag> auftraege = new ArrayList<Auftrag>();
    private Auftrag selectedAuftrag;
    private String loggedInUser;
    private String loggedInRole;
    private Set<Angebot> angebote = new HashSet<>();
    private Person person;

    @PostConstruct
    public void init() {
	loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);

	if (sessionContext.isCallerInRole("AdminUser")) {
	    loggedInRole = "adminUser";
	} else if (sessionContext.isCallerInRole("CustomerUser")) {
	    loggedInRole = "CustomerUser";
	} else if (sessionContext.isCallerInRole("ServiceUser")) {
	    loggedInRole = "ServiceUser";
	}
    }

    public void onLoad() {
	auftraege = auftragControl.findAllAuftraege();
	person = personControl.findPersonByEmail(loggedInUser);
	angebote = person.getAngebote();
    }

    public String erstelleAngebot(Auftrag pAuftrag) {
	this.setSelectedAuftrag(pAuftrag);
	return "/faces/service/newAngebot.xhtml?faces-redirect=true&auftragid=" + pAuftrag.getId();
    }

    public String getLoggedInRole() {
	return loggedInRole;
    }

    public void setLoggedInRole(String loggedInRole) {
	this.loggedInRole = loggedInRole;
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

    public Set<Angebot> getAngebote() {
	return angebote;
    }

    public void setAngebote(Set<Angebot> angebote) {
	this.angebote = angebote;
    }

}
