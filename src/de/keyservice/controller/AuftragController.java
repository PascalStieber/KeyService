package de.keyservice.controller;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.keyservice.entity.Angebot;
import de.keyservice.entity.Auftrag;


@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AuftragController {


	@PersistenceContext(type=PersistenceContextType.EXTENDED, unitName="ExampleDS")
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Auftrag saveAuftrag(Auftrag pAuftrag){ 
		entityManager.persist(pAuftrag);
		entityManager.flush();
		entityManager.refresh(pAuftrag);
		return pAuftrag;
	}

	public Angebot findAngebotByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}