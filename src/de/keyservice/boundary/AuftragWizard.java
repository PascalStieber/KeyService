package de.keyservice.boundary;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.PersonController;
import de.keyservice.entity.Person;

@Stateful
@SessionScoped
@Named("auftragwizard")
public class AuftragWizard {

    @Inject
    PersonController pc = null;

    Person person = new Person();
    

    private int wizardStatusNR = 0;
    private boolean inHurry = false;
    private String doorDetails = "";
    private int doorOption = 0;
    
    
    public String getDoorDetails() {
        return doorDetails;
    }
    public void setDoorDetails(String doorDetails) {
	System.out.println(doorDetails);
        this.doorDetails = doorDetails;
    }
    
    public boolean isInHurry() {
        return inHurry;
    }
    public void setInHurry(boolean inHurry) {
        this.inHurry = inHurry;
    }
    public int getDoorOption() {
        return doorOption;
    }
    public void setDoorOption(int doorOption) {	
        this.doorOption = doorOption;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }
    
    public void setActuallWizardStatus(){
	wizardStatusNR+=wizardStatusNR;
    }
    
    public int getActuallWizardStatus(){
	return wizardStatusNR;
    }
}
