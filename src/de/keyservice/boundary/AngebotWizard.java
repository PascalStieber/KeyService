package de.keyservice.boundary;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AngebotController;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;
import de.keyservice.entity.Posten;

@Stateful
@SessionScoped
@Named("angebotwizard")
public class AngebotWizard {

    @Inject
    AngebotController angebotControl;
    
    private Auftrag auftrag;
    private Posten posten;
    private Set<Posten> gesamtPosten;
    private Angebot angebot;
    private long auftragID;

    
    
    public void init(){
	angebot = new Angebot();
	angebotControl.saveAngebot(angebot);
	
	posten = new Posten();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
	angebotControl.updateAngebot(angebot);
    }
    
    public String addPosten() {
	posten = new Posten();
	angebot.addPosten(posten);
	posten.setAngebot(angebot);
	angebotControl.updateAngebot(angebot);
	
	return "/faces/service/newAngebot?faces-redirect=true";
    }

    public void sendeAngebot() {
	speicherAngebot();
	
    }
    
    public void speicherAngebot(){
	
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
