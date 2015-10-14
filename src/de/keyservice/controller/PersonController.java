package de.keyservice.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import de.keyservice.entity.Person;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PersonController implements Serializable{

    private static final long serialVersionUID = -3994640385430567642L;
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "ExampleDS")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Person savePerson(Person pPerson) {
	entityManager.persist(pPerson);
	entityManager.flush();
	entityManager.refresh(pPerson);
	return pPerson;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Person updatePerson(Person pPerson) {
	entityManager.merge(pPerson);
	entityManager.flush();
	entityManager.refresh(pPerson);
	return pPerson;
    }
    
    public Person findPersonByID(Long id) {
	// TODO Auto-generated method stub
	return null;
    }
    
    public List<Person> findPersonByEmail(String pEmail) {
	entityManager.clear();
	TypedQuery<Person> query = entityManager.createNamedQuery("Person.findByEmail", Person.class);
	query.setParameter("emailAdresse", pEmail);
	List<Person> lPersonListe = query.getResultList();
	return lPersonListe;
    }
}