package de.keyservice.boundary;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.PersonController;
import de.keyservice.entity.Adresse;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.Person;

@Stateful
@SessionScoped
@Named("kundenservice")
public class KundenService {

    @Resource
    private SessionContext sessionContext;
    @Inject
    PersonController personControl;
    
    Person person = new Person();
    Adresse adresse = new Adresse();
    Set<Auftrag> auftraege;

    @PostConstruct
    public void init() {
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	List<Person> lLoggedInPersonen = personControl.findPersonByEmail(loggedInUser);
	for (Person lPerson : lLoggedInPersonen) {
	    System.out.println("!"+lPerson.getVorname());
	    this.person = lPerson;
	    for (Adresse lAdresse : lPerson.getAdressen()) {
		this.adresse = lAdresse;
		System.out.println("!Adressen" + adresse.getStrasse());
	    }
	    for (Auftrag lAuftrag : lPerson.getAuftraege()) {
		System.out.println("=" + lAuftrag.getDoorDetails());
	    }
	    for (Angebot lAngebot : lPerson.getAngebote()) {
		System.out.println("=!" + lAngebot.getDoorDetails());
	    }
	      
	    this.auftraege = lPerson.getAuftraege();
	    System.out.println(">>>die scheisse ist nicht leer" + auftraege.isEmpty());
	    
	}
    }
    
    public String getLoggedInUser(){
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	System.out.println(loggedInUser);
	return loggedInUser;
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