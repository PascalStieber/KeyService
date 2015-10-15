package de.keyservice.boundary;

import java.io.Serializable;
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
import de.keyservice.entity.Adresse;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
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

    Person person = new Person();
    Adresse adresse = new Adresse();
    Auftrag auftrag;
    Set<Auftrag> auftraege;
    Set<Angebot> angebote;
    long auftragID;
    
    @PostConstruct
    public void init() {
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);

	for (Adresse lAdresse : person.getAdressen()) {
	    this.adresse = lAdresse;
	}
	this.auftraege = person.getAuftraege();
    }
    
    public void akzeptiereAngebot(Angebot angebot){
	
    }
    
    public String getAngeboteLink(Auftrag pAuftrag){
	return "showAllAngebote.xhtml?faces-redirect=true&auftragid=" + pAuftrag.getId();
    }
    
    public void receiveNewAngebote(){
	auftrag = auftragControl.findAuftragByID(auftragID);
	angebote = auftrag.getAngebote();
    }

    public Set<Angebot> getAngebote() {
        return angebote;
    }

    public void setAngebote(Set<Angebot> angebote) {
        this.angebote = angebote;
    }

    public String getLoggedInUser() {
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	return loggedInUser;
    }

    public String getLoggedInRole() {
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	return loggedInUser;
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

    public Set<Auftrag> getAuftraege() {
	return auftraege;
    }

    public void setAuftraege(Set<Auftrag> auftraege) {
	this.auftraege = auftraege;
    }

}
