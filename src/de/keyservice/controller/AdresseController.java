package de.keyservice.controller;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.keyservice.entity.Adresse;


@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AdresseController {


	@PersistenceContext(type=PersistenceContextType.EXTENDED, unitName="ExampleDS")
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Adresse saveAdresse(Adresse pAdresse){ 
		entityManager.persist(pAdresse);
		entityManager.flush();
		entityManager.refresh(pAdresse);
		return pAdresse;
	}

	public Adresse findAdresseByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}