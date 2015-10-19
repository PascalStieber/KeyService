package de.keyservice.entity;

public class ContractEvent {
    
    private Person person;
    private Auftrag auftrag;
    private Angebot angebot;
    

    public ContractEvent(Auftrag pAuftrag){
	auftrag = pAuftrag;
	person = pAuftrag.getPerson();
    }
    
    public ContractEvent(Auftrag pAuftrag, Angebot pAngebot){
	auftrag = pAuftrag;
	person = pAuftrag.getPerson();
	angebot = pAngebot;
    }

    
    public Angebot getAngebot() {
        return angebot;
    }

    public void setAngebot(Angebot angebot) {
        this.angebot = angebot;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public Auftrag getAuftrag() {
	return auftrag;
    }

    public void setAuftrag(Auftrag auftrag) {
	this.auftrag = auftrag;
    }
    
    
}
