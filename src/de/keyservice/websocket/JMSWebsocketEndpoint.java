package de.keyservice.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import javax.enterprise.event.Observes;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.ContractEvent;

@Stateful
@ServerEndpoint(value = "/jmstopicreader")
public class JMSWebsocketEndpoint {

    static Set<Auftrag> auftraege = new HashSet<Auftrag>();
    // static Set<Session> clients = new HashSet<Session>();
    static LinkedHashMap<String, Session> kunde = new LinkedHashMap<>();
    static LinkedHashMap<String, Session> dienstleister = new LinkedHashMap<>();
    static ArrayList<Session> service = new ArrayList<>();

    public static void receiveFiredAuftraege(@Observes ContractEvent pAuftragEvent) throws IOException {
	System.out.println("Websocket: auftrag wurde gefired ");

	// sende an kunden wenn angebot im event vorliegt und es noch nicht
	// akzeptiert ist
	if (pAuftragEvent.getAngebot() != null && pAuftragEvent.getAngebot().isAccepted() == false) {
	    Session lKundenSession = kunde.get(pAuftragEvent.getPerson().getEmailAdresse());
	    if (lKundenSession.isOpen()) {
		lKundenSession.getBasicRemote().sendText("message:newAngebot!");
	    }
	    // sende an auftragnehmer (dienstleister), wenn angebot vorhanden
	    // und angbot akzeptiert wurde
	} else if (pAuftragEvent.getAngebot() != null && pAuftragEvent.getAngebot().isAccepted()) {
	    Session lDienstleisterSession = dienstleister.get(pAuftragEvent.getAngebot().getPerson().getEmailAdresse());
	    if (lDienstleisterSession.isOpen()) {
		lDienstleisterSession.getBasicRemote().sendText("message:isAccepted!");
	    }
	} else {
	    // ansonsten sende an alle service anbieter
	    for (Session lServiceSession : service) {
		if (lServiceSession.isOpen()) {
		    lServiceSession.getBasicRemote().sendText("message:newAuftrag!");
		}
	    }
	}
    }

    @OnOpen
    public void open(Session session) {
	System.out.println("Open session:" + session.getId());
    }

    @OnClose
    public void close(Session session, CloseReason c) {
	System.out.println("Closing:" + session.getId());

    }

    @OnMessage
    public void onMessage(String message, Session session) throws EncodeException {
	if (message.split(":")[1].equals("CustomerUser")) {
	    kunde.put(message.split(":")[0], session);
	} else if (message.split(":")[1].equals("ServiceUser")) {
	    service.add(session);
	    dienstleister.put(message.split(":")[0], session);
	}

	System.out.println("empfange " + message + message.split(":"));

    }

}
