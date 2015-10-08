package de.keyservice.controller;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.QueryHint;
import javax.persistence.TypedQuery;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.Person;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PersonController {

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