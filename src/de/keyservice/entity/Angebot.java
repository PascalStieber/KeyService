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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@NamedQueries({ @NamedQuery(name = "Angebot.findAll", query = "SELECT a FROM Angebot a "),
	@NamedQuery(name = "Angebot.findByID", query = "SELECT a FROM Angebot a WHERE a.id = :id") })
@Entity
@Table(name = "Angebot")
public class Angebot implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    @Version
    private int versionNr;
    @Transient
    private boolean editable;
    @ManyToOne
    private Person person;
    private boolean isAccepted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "angebot", fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private Set<Posten> posten = new HashSet<Posten>();
    @ManyToOne
    private Auftrag auftrag;

    public Auftrag getAuftrag() {
	return auftrag;
    }

    public void setAuftrag(Auftrag auftrag) {
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

    public boolean isAccepted() {
	return isAccepted;
    }

    public boolean getIsAccepted() {
	return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
	this.isAccepted = isAccepted;
    }
}