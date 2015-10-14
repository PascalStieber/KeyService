package de.keyservice.entity;

public class AuftragEvent {

    private Auftrag auftrag = new Auftrag();

    public AuftragEvent(Auftrag pAuftrag){
	auftrag = pAuftrag;
    }
    
    public Auftrag getAuftrag() {
	return auftrag;
    }

    public void setAuftrag(Auftrag auftrag) {
	this.auftrag = auftrag;
    }
    
    
}
