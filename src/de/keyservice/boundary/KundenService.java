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
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

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

    Person person = new Person();
    Adresse adresse = new Adresse();
    Auftrag auftrag;
    List<Auftrag> auftraege = new ArrayList<Auftrag>();
    Set<Angebot> angebote = new HashSet<Angebot>();
    long auftragID = 0;
    String loggedInUser;

    @PostConstruct
    public void init() {
	loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);
    }

    public void onLoad() {
	auftraege = auftragControl.findAllAuftraege();
	auftrag = auftragControl.findAuftragByID(auftragID);
    }

    public void akzeptiereAngebot(Angebot angebot) {

    }

    public String getAngeboteLink(Auftrag pAuftrag) {
	return "showAllAngebote.xhtml?faces-redirect=true&auftragid=" + pAuftrag.getId();
    }

    public void onreceiveNewAngebote(@Observes ContractEvent pContractEvent) {

	System.out.println(">>>>>>" + loggedInUser);
	auftrag = auftragControl.findAuftragByID(auftragID);

	// // is ContractEvent relevant fuer angemeldeten benutzer
	// if (pContractEvent.getAngebot() != null) {
	//// if
	// (loggedInUser.equals(pContractEvent.getPerson().getEmailAdresse())) {
	// try {
	// angebote.add((Angebot) pContractEvent.getAngebot().clone());
	// } catch (CloneNotSupportedException e) {
	// e.printStackTrace();
	// }
	//// }
	// }
    }

    public Set<Angebot> getAngebote() {
	System.out.println(auftragID);
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

    public void setLoggedInUser(String loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

}
