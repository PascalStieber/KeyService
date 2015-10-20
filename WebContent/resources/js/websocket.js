var endpoint = new WebSocket("ws://localhost:8080/KeyService/jmstopicreader");

function sendMessage() {
	endpoint.send("hallo welt");
};

function consoleWebsocketSession(){
	console.log("closeWebsocketSession wurde aufgerufen");
	endpoint.close();
}

function openWebsocketSession(){
	console.log("openWebsocketSession wurde aufgerufen");

	endpoint.OPEN;
	alert(hallo);

	endpoint.send("messafgw");
	
}


endpoint.onopen = function()
{
	var user =  document.getElementById('loggedInUser').value
	var role = document.getElementById('loggedInRole').value
	var message =  String.concat(user, ":", role);
	endpoint.send("halloooo2");
	console.log("websocket: wurde geöffnet");
};

endpoint.onclose = function() {
	console.log("websocket: wurde geschlossen");
};


endpoint.onmessage = function(evt) {
	console.log("websocket: nachricht wurde empfangen");
//	window.location.reload(false);
	window.location.replace(window.location.pathname);
};

