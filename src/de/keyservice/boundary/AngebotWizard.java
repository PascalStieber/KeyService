package de.keyservice.boundary;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AngebotController;
import de.keyservice.controller.AuftragController;
import de.keyservice.controller.PersonController;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.ContractEvent;
import de.keyservice.entity.Person;
import de.keyservice.entity.Posten;

@Stateful
@SessionScoped
@Named("angebotwizard")
public class AngebotWizard {

    @Inject
    Event<ContractEvent> contractEvent;
    @Inject
    AngebotController angebotControl;
    @Resource
    private SessionContext sessionContext;
    @Inject
    PersonController personControl;
    @Inject
    AuftragController auftragControl;

    private Auftrag auftrag = new Auftrag();
    private Posten posten = new Posten();
    private Angebot angebot = new Angebot();
    private long auftragID = 0;
    private Person person = new Person();
    String loggedInUser;

    @PostConstruct
    public void init() {
	loggedInUser = sessionContext.getCallerPrincipal().getName();
	person = personControl.findPersonByEmail(loggedInUser);

	angebot.addPosten(posten);
	posten.setAngebot(angebot);
    }

    public void onLoad() {
	auftrag = auftragControl.findAuftragByID(auftragID);
    }

    public void addPosten() {
	posten = new Posten();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
    }

    public String sendeAngebot() {
	speicherAngebot();
	contractEvent.fire(new ContractEvent(auftrag, angebot));
	return "/faces/service/showAllAuftraege.xhtml";
    }

    public void speicherAngebot() {
	angebot.setAuftrag(auftrag);
	angebot.setPerson(person);
	angebotControl.saveAngebot(angebot);
    }

    // für das nächste Angebot alles zurücksetzen
    public void resetForNewAngebot() {
	angebot = new Angebot();
	posten = new Posten();
	auftrag = new Auftrag();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
	angebotControl.saveAngebot(angebot);
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
