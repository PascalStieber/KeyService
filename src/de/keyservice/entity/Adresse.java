package de.keyservice.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "Adresse")
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private String strasse;
    private String plz;
    private String hausnummer;
    private String ort;
    @Version
    private int versionNr;
    @Transient
    private boolean editable;
    
    @ManyToMany(mappedBy="adressen")
    private Set<Person> personen = new HashSet<Person>();
   
    @OneToMany(cascade=CascadeType.ALL, mappedBy="adresse", fetch = FetchType.LAZY)
    private Set<Auftrag> auftraege = new HashSet<Auftrag>();
    
    public void addAuftrag(Auftrag pAuftrag){
	this.auftraege.add(pAuftrag);
    }
    
    public Set<Auftrag> getAuftraege(){
	return this.auftraege;
    }
    
    
    public void addPerson(Person pPerson) {
	this.personen.add(pPerson);
    }
    
    public Set<Person> getPersonen() {
	return this.personen;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getStrasse() {
	return strasse;
    }

    public void setStrasse(String strasse) {
	this.strasse = strasse;
    }

    public String getPlz() {
	return plz;
    }

    public void setPlz(String plz) {
	this.plz = plz;
    }

    public String getHausnummer() {
	return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
	this.hausnummer = hausnummer;
    }

    public String getOrt() {
	return ort;
    }

    public void setOrt(String ort) {
	this.ort = ort;
    }

    public boolean isEditable() {
	return editable;
    }

    public void setEditable(boolean editable) {
	this.editable = editable;
    }

}