package de.keyservice.boundary;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AngebotController;
import de.keyservice.controller.AuftragController;
import de.keyservice.controller.PersonController;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.Person;
import de.keyservice.entity.Posten;

@Stateful
@SessionScoped
@Named("angebotwizard")
public class AngebotWizard {

    @Inject
    AngebotController angebotControl;
    @Resource
    private SessionContext sessionContext;
    @Inject
    PersonController personControl;
    @Inject
    AuftragController auftragControl;
    
    private Auftrag auftrag;
    private Posten posten;
    private Angebot angebot;
    private long auftragID;
    private Person person;
    String loggedInUser;
    
    @PostConstruct
    public void init(){
	loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);
	
	
	angebot = new Angebot();
	angebotControl.saveAngebot(angebot);
	posten = new Posten();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
	angebotControl.updateAngebot(angebot);
    }
    
    public void addPosten() {
	angebotControl.updateAngebot(angebot);
	posten = new Posten();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
	angebotControl.updateAngebot(angebot);
	
    }

    public String sendeAngebot() {
	speicherAngebot();
	//für das nächste Angebot alles zurücksetzen
	angebot = new Angebot();
	posten = new Posten();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
	angebotControl.saveAngebot(angebot);
	return "/faces/service/showAllAuftraege?faces-redirect=true";
    }
    
    public void speicherAngebot(){
	auftrag = auftragControl.findAuftragByID(auftragID);
	angebot.setAuftrag(auftrag);
	angebot.setPerson(person);
	angebotControl.updateAngebot(angebot);
    }
    
    public Auftrag getAuftrag() {
        return auftrag;
    }

    public void setAuftrag(Auftrag auftrag) {
        this.auftrag = auftrag;
    }

    public Posten getPosten() {
        return posten;
    }

    public void setPosten(Posten posten) {
        this.posten = posten;
    }

    public Angebot getAngebot() {
        return angebot;
    }

    public void setAngebot(Angebot angebot) {
        this.angebot = angebot;
    }

    public long getAuftragID() {
        return auftragID;
    }

    public void setAuftragID(long auftragID) {
        this.auftragID = auftragID;
    }
}
