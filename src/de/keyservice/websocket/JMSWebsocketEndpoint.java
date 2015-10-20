package de.keyservice.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ejb.Stateful;
import javax.enterprise.event.Observes;
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
    static LinkedHashMap<String, Session> clients = new LinkedHashMap<>();

    public static void receiveFiredAuftraege(@Observes ContractEvent pAuftragEvent) throws IOException {
	System.out.println("Websocket: auftrag wurde gefired ");
	Session lSession = clients.get(pAuftragEvent.getPerson().getEmailAdresse());
	System.out.println(clients.get(pAuftragEvent.getPerson().getEmailAdresse()));
	

	if (lSession != null) {
	    if (pAuftragEvent.getAngebot() != null) {
		//sende an alle service 
		lSession.getBasicRemote().sendText("message:angebot!");
	    } else if (!clients.isEmpty()) {
		//sende an kunden
		lSession.getBasicRemote().sendText("message:auftrag!");
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
	clients.put(message.split(":")[0], session);
	System.out.println("empfange " + message );
	
    }
}
