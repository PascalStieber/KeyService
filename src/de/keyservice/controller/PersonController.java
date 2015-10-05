package de.keyservice.controller;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.keyservice.entity.Person;


@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PersonController {


	@PersistenceContext(type=PersistenceContextType.EXTENDED, unitName="ExampleDS")
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Person savePerson(Person pPerson){ 
		entityManager.persist(pPerson);
		entityManager.flush();
		entityManager.refresh(pPerson);
		return pPerson;
	}

	public Person findPersonByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}