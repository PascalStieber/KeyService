package de.keyservice.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "Angebot")
public class Angebot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private int wizardStatusNR;
    private boolean inHurry;
    private String doorDetails;
    private int doorOption;
    @Version
    private int versionNr;
    @Transient
    private boolean editable;
    @ManyToOne
    private Person person;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="angebot", fetch = FetchType.LAZY)
    private Set<Posten> posten = new HashSet<Posten>();
    @ManyToOne
    private Person auftrag;
    
    public Person getAuftrag() {
        return auftrag;
    }
    public void setAuftrag(Person auftrag) {
        this.auftrag = auftrag;
    }
    public Set<Posten> getPosten() {
        return posten;
    }
    public void addPosten(Posten pPosten) {
        this.posten.add(pPosten);
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
}