package de.keyservice.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AngebotController;
import de.keyservice.controller.AuftragController;
import de.keyservice.controller.PersonController;
import de.keyservice.entity.Adresse;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.ContractEvent;
import de.keyservice.entity.Person;

@Stateful
@SessionScoped
@Named("kundenservice")
public class KundenService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Resource
    private SessionContext sessionContext;
    @Inject
    PersonController personControl;
    @Inject
    AuftragController auftragControl;
    @Inject
    AngebotController angebotControl;
    @Inject
    Event<ContractEvent> contractEvent;
    
    Person person = new Person();
    Adresse adresse = new Adresse();
    Auftrag auftrag;
    List<Auftrag> auftraege = new ArrayList<Auftrag>();
    Set<Angebot> angebote = new HashSet<Angebot>();
    long auftragID = 0;
    String loggedInUser;
    String loggedInRole;



    @PostConstruct
    public void init() {
	loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);
	
	if(sessionContext.isCallerInRole("AdminUser")){
	    loggedInRole = "adminUser";
	}else if (sessionContext.isCallerInRole("CustomerUser")) {
	    loggedInRole = "CustomerUser";
	}else if (sessionContext.isCallerInRole("ServiceUser")) {
	    loggedInRole = "ServiceUser";
	}
    }

    public void onLoad() {
	auftraege = auftragControl.findAllAuftraege();
	auftrag = auftragControl.findAuftragByID(auftragID);
    }

    public void akzeptiereAngebot(Angebot angebot) {
	angebot.setAccepted(true);
	angebotControl.updateAngebot(angebot);
	contractEvent.fire(new ContractEvent(auftrag, angebot));
    }

    public String getAngeboteLink(Auftrag pAuftrag) {
	return "showAllAngebote.xhtml?faces-redirect=true&auftragid=" + pAuftrag.getId();
    }

    public Set<Angebot> getAngebote() {
	System.out.println(auftragID);
	return angebote;
    }

    public void setAngebote(Set<Angebot> angebote) {
	this.angebote = angebote;
    }

    public long getAuftragID() {
	return auftragID;
    }

    public void setAuftragID(long auftragID) {
	this.auftragID = auftragID;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    public Adresse getAdresse() {
	return adresse;
    }

    public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
    }

    public List<Auftrag> getAuftraege() {
	return auftraege;
    }

    public void setAuftraege(List<Auftrag> auftraege) {
	this.auftraege = auftraege;
    }

    public Auftrag getAuftrag() {
	return auftrag;
    }

    public void setAuftrag(Auftrag auftrag) {
	this.auftrag = auftrag;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLoggedInRole() {
        return loggedInRole;
    }

    public void setLoggedInRole(String loggedInRole) {
        this.loggedInRole = loggedInRole;
    }



}
