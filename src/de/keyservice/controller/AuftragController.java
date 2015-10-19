package de.keyservice.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.Person;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AuftragController implements Serializable {

    private static final long serialVersionUID = 6108542376443771951L;

    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "ExampleDS")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Auftrag saveAuftrag(Auftrag pAuftrag) {
	entityManager.persist(pAuftrag);
	entityManager.flush();
	entityManager.refresh(pAuftrag);
	return pAuftrag;
    }

    public Auftrag getLatestAuftrag(Person pPerson) {
	TypedQuery<Auftrag> query = entityManager.createNamedQuery("Auftrag.findLatestAuftrag", Auftrag.class);
	query.setParameter("person", pPerson);
	return query.getResultList().get(0);
    }

    public List<Auftrag> findAllAuftraege() {
	TypedQuery<Auftrag> query = entityManager.createNamedQuery("Auftrag.findAll", Auftrag.class);
	return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Auftrag findAuftragByID(long pID) {
	Auftrag lAuftrag = entityManager.find(Auftrag.class, pID);
//	damit die entity nicht aus dem speicher geholt wird.
	if (lAuftrag != null) {
	    entityManager.merge(lAuftrag);
	    entityManager.refresh(lAuftrag);
	}
	return lAuftrag;
    }
}