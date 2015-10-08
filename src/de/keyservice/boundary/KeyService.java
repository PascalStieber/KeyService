package de.keyservice.boundary;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.keyservice.controller.PersonController;
import de.keyservice.entity.Person;

@Stateful
@SessionScoped
@Named("keyservice")
public class KeyService {

    @Inject
    PersonController pc;
    @Resource
    private SessionContext sessionContext;

    Person person = new Person();

    
    
    
    public String getAdminLink() {
//	Anmelden, Abmelden, ...
// 	LoginContext lLoginContext = new LoginContext("adminDomain");
//	String loggedInUser = sessionContext.getCallerPrincipal().getName();
//	boolean isUserPermitted = sessionContext.isCallerInRole("adminUser");
//	System.out.println(loggedInUser);
//	System.out.println(isUserPermitted);
	return "/faces/admin/index.xhtml?faces-redirect=true";
    }

    public String getKundenLink() {
	return "/faces/kunde/index.xhtml?faces-redirect=true";
    }

    public String getServiceLink() {
	return "/faces/service/index.xhtml?faces-redirect=true";
    }

    public String getRegisterLink() {
	return "/faces/user/index.xhtml?faces-redirect=true";
    }
    
    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }
}
