package de.keyservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
@NamedQueries({
	@NamedQuery(name = "Auftrag.findLatestAuftrag", query = "SELECT a FROM Auftrag a WHERE a.person = :person ORDER BY a.datum DESC") })
@Entity
@Table(name = "Auftrag")
public class Auftrag implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    public Auftrag() {
    }

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