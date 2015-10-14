var endpoint = new WebSocket("ws://localhost:8080/KeyService/jmstopicreader");

function receiveNewMessages() {
	endpoint.send("hallo welt");
	// alert("schrott");
};

endpoint.onopen = function()
{
//	alert("websocket: wurde geöffnet");
};

endpoint.onclose = function() {
//	alert("websocket: wurde geschlossen");
};

var auftraege;

endpoint.onmessage = function(evt) {
	alert(evt.data);
	auftraege = evt.data;
	alert("websocket: nachrichten wurden empfangen");
	
};


function getAuftraege(){
	return auftraege;
};