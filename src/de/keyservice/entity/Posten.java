package de.keyservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Posten")
public class Posten {

    @Id
    @GeneratedValue
    private long id;
    private String bezeichnung;
    private String betrag;
    @ManyToOne
    private Angebot angebot;
    
    
    public String getBezeichnung() {
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public String getBetrag() {
        return betrag;
    }
    public void setBetrag(String betrag) {
        this.betrag = betrag;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Angebot getAngebot() {
        return angebot;
    }
    public void setAngebot(Angebot angebot) {
        this.angebot = angebot;
    }
    
    
}
