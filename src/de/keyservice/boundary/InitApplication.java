package de.keyservice.boundary;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.AdresseController;
import de.keyservice.controller.AngebotController;
import de.keyservice.controller.PersonController;
import de.keyservice.entity.Adresse;
import de.keyservice.entity.Angebot;
import de.keyservice.entity.Person;

@SessionScoped
@Stateful
@Named("initApplication")
public class InitApplication {

    @Inject
    private PersonController personControl;
    @Inject
    private AdresseController adresseControl;
    @Inject
    private AngebotController angebotControl;
    
    public void init() {
	
	Adresse lAdresse1 = new Adresse();
	lAdresse1.setHausnummer("7");
	lAdresse1.setOrt("Essen");
	lAdresse1.setPlz("45136");
	lAdresse1.setStrasse("Sabinastr.");
	
	
	Adresse lAdresse2 = new Adresse();
	lAdresse2.setHausnummer("23");
	lAdresse2.setOrt("Essen");
	lAdresse2.setPlz("45136");
	lAdresse2.setStrasse("Hallostr.");
	
	Person lPerson1 = new Person();
	lPerson1.setVorname("Pascal");
	lPerson1.setNachname("Stieber");
	lPerson1.setEmailAdresse("pascal.stieber.ps@googlemail.com");
	lPerson1.setPasswort("hallowelt");
	lPerson1.setBild(null);
	lPerson1.setUserRole("AdminUser");
	personControl.savePerson(lPerson1);
	
	

	Person lPerson2 = new Person();
	lPerson2.setVorname("Tim");
	lPerson2.setNachname("Foerster");
	lPerson2.setEmailAdresse("foerster@dailytrade24.de");
	lPerson2.setPasswort("hallowelt");
	lPerson2.setBild(null);
	lPerson2.setUserRole("ServiceUser");
	
	Angebot lAngebot1 = new Angebot();
	lAngebot1.setPerson(lPerson2);
	Angebot lAngebot2 = new Angebot();
	lAngebot2.setPerson(lPerson2);
	lPerson2.addAngebot(lAngebot1);
	lPerson2.addAngebot(lAngebot2);
	personControl.savePerson(lPerson2);
	
	
	lPerson1.addAdresse(lAdresse2);
	lPerson1.addAdresse(lAdresse1);
//	personControl.savePerson(lPerson1);
	
	lAdresse1.addPerson(lPerson2);
	lAdresse1.addPerson(lPerson1);
	adresseControl.saveAdresse(lAdresse1);

	// File lFile1 = new
	// File("F:\\\\Development_Repos\\git\\MediCompare\\MediCompare\\WebContent\\resources\\img\\produktBilder\\diclofenac_dura.jpg");
	// byte[] lbyte1 = new byte[(int)lFile1.length()];
	//
	// try {
	// FileInputStream lFileIS1 = new FileInputStream(lFile1);
	// lFileIS1.read(lbyte1);
	// lFileIS1.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// medikament1.setBild(lbyte1);
	

	
	
    }
}
