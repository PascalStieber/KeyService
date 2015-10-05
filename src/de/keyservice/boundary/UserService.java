package de.keyservice.boundary;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Stateful
@SessionScoped
@Named("userservice")
public class UserService {

    @Resource
    private SessionContext sessionContext;

    public String getLoggedInUser(){
	String loggedInUser = sessionContext.getCallerPrincipal().getName();
	System.out.println(loggedInUser);
	return loggedInUser;
    }

}
