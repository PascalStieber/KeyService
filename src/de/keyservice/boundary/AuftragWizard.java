package de.keyservice.boundary;

import java.util.List;

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
import de.keyservice.entity.Auftrag;
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
    
    @Resource
    private SessionContext sessionContext;

    Person person = new Person();
    Adresse adresse = new Adresse();
    Auftrag auftrag = new Auftrag();

    @PostConstruct
    private void init() {
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	List<Person> lLoggedInPersonen = personControl.findPersonByEmail(loggedInUser);
	for (Person lPerson : lLoggedInPersonen) {
	    this.person = lPerson;
	    for (Adresse lAdresse : lPerson.getAdressen()) {
		this.adresse = lAdresse;
	    }
	}
    }

    public String sendeAuftrag(){
	vertragTopicSender.sendAuftrag(auftrag);	
	speicherAuftrag();
	return "/faces/kunde/showAllAuftraege.xhtml?faces-redirect=true";
    }
    
    public void speicherAuftrag(){
	auftrag.setPerson(person);
	person.addAdresse(adresse);
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
