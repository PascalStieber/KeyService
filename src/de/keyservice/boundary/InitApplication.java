package de.keyservice.boundary;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.PersonController;
import de.keyservice.entity.Person;

@SessionScoped
@Stateful 
@Named("initApplication")
public class InitApplication {

	@Inject
	private PersonController personControl;
	
	
	public void init(){
		Person lPerson1 = new Person();		
		lPerson1.setVorname("Pascal");
		lPerson1.setNachname("Stieber");
		lPerson1.setEmailAdresse("pascal.stieber.ps@googlemail.com");
		lPerson1.setPasswort("hallowelt");
		lPerson1.setBild(null);
		personControl.savePerson(lPerson1);
		
		Person lPerson2 = new Person();		
		lPerson2.setVorname("Tim");
		lPerson2.setNachname("Foerster");
		lPerson2.setEmailAdresse("foerster@dailytrade24.de");
		lPerson2.setPasswort("hallowelt");
		lPerson2.setBild(null);
		personControl.savePerson(lPerson2);
		
	
//		File lFile1 = new File("F:\\\\Development_Repos\\git\\MediCompare\\MediCompare\\WebContent\\resources\\img\\produktBilder\\diclofenac_dura.jpg");
//		byte[] lbyte1 = new byte[(int)lFile1.length()];
//		
//		try {
//			FileInputStream lFileIS1 = new FileInputStream(lFile1);
//			lFileIS1.read(lbyte1);
//			lFileIS1.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		medikament1.setBild(lbyte1);
		
		
	}
}
