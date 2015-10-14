package de.keyservice.jms;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.keyservice.entity.Auftrag;
import de.keyservice.entity.AuftragEvent;

@Stateless
@ServerEndpoint(value = "/jmstopicreader")
public class JMSWebsocketEndpoint {

    static Set<Auftrag> auftraege = new HashSet<Auftrag>();
    static Set<Session> clients = new HashSet<Session>();

    public static void receiveFiredAuftraege(@Observes AuftragEvent pAuftragEvent) {
	auftraege.add(pAuftragEvent.getAuftrag());
	System.out.println("Websocket: auftrag wurde gefired ");
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
    public String onMessage(String message, Session session) throws EncodeException {
	// public Set<Auftrag> onMessage(String message, Session session) {
	clients.add(session);

	System.out.println("Auftraege vorhanden? " + auftraege.size() + message);
	// return JMSWebsocketEndpoint.auftraege;
//	try {
//	    if (session.isOpen()) {
//		session.getBasicRemote().sendObject(JMSWebsocketEndpoint.auftraege);
//	    }
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}
	return "hallo zurueck";

	//

    }
}
