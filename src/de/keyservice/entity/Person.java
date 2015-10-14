package de.keyservice.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


@NamedQueries({
	@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true") }),
	@NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.emailAdresse = :emailAdresse") })
@Entity
@Table(name = "Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public Person() {
    }

    @Id
    @GeneratedValue
    private long id;
    private String vorname;
    private String nachname;
    private String emailAdresse;
    private String passwort;
    private String userRole;
    
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] bild;

    public void setBild(byte[] bild) {
	this.bild = bild;
    }
    @Version
    private int versionNr;
    @Transient
    private boolean editable;

    @ManyToMany(cascade = CascadeType.ALL,targetEntity=Adresse.class)
    @JoinTable(name="Person_Adresse")
    private Set<Adresse> adressen = new HashSet<Adresse>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="person", fetch = FetchType.LAZY)
    private Set<Angebot> angebote = new HashSet<Angebot>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy="person", fetch = FetchType.LAZY)
    private Set<Auftrag> auftraege = new HashSet<Auftrag>();
    
    public void addAuftrag(Auftrag pAuftrag){
	this.auftraege.add(pAuftrag);
    }
    
    public Set<Auftrag> getAuftraege(){
	return this.auftraege;
    }
    
    public void addAngebot(Angebot pAngebot){
	this.angebote.add(pAngebot);
    }
    
    public Set<Angebot> getAngebote(){
	return this.angebote;
    }
    
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Set<Adresse> getAdressen() {
	return this.adressen;
    }
    
    public void addAdresse(Adresse pAdresse) {
	this.adressen.add(pAdresse);
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public boolean isEditable() {
	return editable;
    }

    public void setEditable(boolean editable) {
	this.editable = editable;
    }

    public String getVorname() {
	return vorname;
    }

    public void setVorname(String param) {
	this.vorname = param;
    }

    public String getNachname() {
	return nachname;
    }

    public void setNachname(String param) {
	this.nachname = param;
    }

    public String getEmailAdresse() {
	return emailAdresse;
    }

    public void setEmailAdresse(String param) {
	this.emailAdresse = param;
    }

    public String getPasswort() {
	return passwort;
    }

    public void setPasswort(String param) {
	this.passwort = param;
    }

    public byte[] getBild() {
	// TODO Auto-generated method stub
	return null;
    }

}