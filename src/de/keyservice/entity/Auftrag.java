package de.keyservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
@NamedQueries({
	@NamedQuery(name = "Auftrag.findLatestAuftrag", query = "SELECT a FROM Auftrag a WHERE a.person = :person ORDER BY a.datum DESC"),
	@NamedQuery(name = "Auftrag.findAll", query = "SELECT a FROM Auftrag a ORDER BY a.datum DESC"),
	@NamedQuery(name = "Auftrag.findByID", query = "SELECT a FROM Auftrag a WHERE a.id = :id")})
@Entity
@Table(name = "Auftrag")
public class Auftrag implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private int wizardStatusNR;
    private boolean inHurry;
    private String doorDetails;
    private int doorOption;
    private Date datum;
    @Version
    private int versionNr;
    @Transient
    private boolean editable;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Adresse adresse;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="auftrag", fetch = FetchType.LAZY)
    private Set<Angebot> angebote = new HashSet<Angebot>();
    
    
    public Set<Angebot> getAngebote() {
        return angebote;
    }

    public void setAngebote(Set<Angebot> angebote) {
        this.angebote = angebote;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public int getWizardStatusNR() {
	return wizardStatusNR;
    }

    public void setWizardStatusNR(int wizardStatusNR) {
	this.wizardStatusNR = wizardStatusNR;
    }

    public boolean isInHurry() {
	return inHurry;
    }

    public void setInHurry(boolean inHurry) {
	this.inHurry = inHurry;
    }

    public String getDoorDetails() {
	return doorDetails;
    }

    public void setDoorDetails(String doorDetails) {
	this.doorDetails = doorDetails;
    }

    public int getDoorOption() {
	return doorOption;
    }

    public void setDoorOption(int doorOption) {
	this.doorOption = doorOption;
    }

    public int getVersionNr() {
	return versionNr;
    }

    public void setVersionNr(int versionNr) {
	this.versionNr = versionNr;
    }

    public boolean isEditable() {
	return editable;
    }

    public void setEditable(boolean editable) {
	this.editable = editable;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
	return super.clone();
    }

    public Date getDatum() {
	return datum;
    }

    public void setDatum(Date datum) {
	this.datum = datum;
    }
}