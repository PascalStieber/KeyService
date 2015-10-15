package de.keyservice.boundary;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AuftragController;
import de.keyservice.controller.PersonController;
import de.keyservice.entity.Adresse;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.AuftragEvent;
import de.keyservice.entity.Person;
import de.keyservice.jms.VertragTopicSender;

@Stateful
@SessionScoped
@Named("auftragwizard")
public class AuftragWizard {

    @Inject
    PersonController personControl;
    @Inject
    AuftragController auftragControl;
    @Inject
    VertragTopicSender vertragTopicSender;
    @Inject
    Event<AuftragEvent> auftragEvent;

    @Resource
    private SessionContext sessionContext;

    Person person = new Person();
    Adresse adresse = new Adresse();
    Auftrag auftrag = new Auftrag();

    String loggedInUser;

    @PostConstruct
    public void init() {
	loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);
	System.out.println(loggedInUser + person.getEmailAdresse());
	for (Adresse lAdresse : person.getAdressen()) {
	    this.adresse = lAdresse;
	}
    }

    public String sendeAuftrag() {
	speicherAuftrag();
	Auftrag lAuftrag = getLatestAuftrag();
	// vertragTopicSender.sendAuftrag(lAuftrag);
	auftragEvent.fire(new AuftragEvent(lAuftrag));
	return "/faces/kunde/showAllAuftraege.xhtml?faces-redirect=true";
    }

    public Auftrag getLatestAuftrag() {
	return auftragControl.getLatestAuftrag(this.person);
    }

    public void speicherAuftrag() {
	auftrag.setDatum(new Date());
	auftrag.setPerson(person);
	person.addAdresse(adresse);
	adresse.addAuftrag(auftrag);
	auftrag.setAdresse(adresse);
	person.addAuftrag(auftrag);
	adresse.addPerson(person);
	personControl.updatePerson(person);
    }

    public Auftrag getAuftrag() {
	return auftrag;
    }

    public void setAuftrag(Auftrag auftrag) {
	this.auftrag = auftrag;
    }

    public String editAdresse(Adresse pAdresse) {
	pAdresse.setEditable(true);
	return null;
    }

    public String neueAdresse() {
	adresse = new Adresse();
	adresse.setEditable(true);
	return null;
    }

    public Adresse getAdresse() {
	return adresse;
    }

    public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

}
