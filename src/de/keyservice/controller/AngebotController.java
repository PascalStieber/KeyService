package de.keyservice.controller;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.keyservice.entity.Angebot;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AngebotController implements Serializable {

    private static final long serialVersionUID = -3949675965848077624L;

    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "ExampleDS")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Angebot saveAngebot(Angebot pAngebot) {
	entityManager.persist(pAngebot);
	entityManager.flush();
	entityManager.refresh(pAngebot);
	return pAngebot;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Angebot updateAngebot(Angebot pAngebot) {
	entityManager.merge(pAngebot);
	entityManager.flush();
	entityManager.refresh(pAngebot);
	return pAngebot;
    }

    public Angebot findAngebotByID(long pID) {
	return entityManager.find(Angebot.class, pID);
    }
}