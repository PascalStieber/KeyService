var endpoint = new WebSocket("ws://localhost:8080/KeyService/jmstopicreader");

function sendMessage() {
	endpoint.send("hallo welt");
};
function consoleWebsocketSession(){
	console.log("closeWebsocketSession wurde aufgerufen");
	endpoint.close();
};
function openWebsocketSession(){
	console.log("openWebsocketSession wurde aufgerufen");
	endpoint.send("message");
	
};
endpoint.onopen = function()
{
	var user =  document.getElementById('userForm:loggedInUser').value
	var role = document.getElementById('userForm:loggedInRole').value
	var message =  user.concat(":", role);
//	alert(message);
	endpoint.send(message);
	console.log("websocket: wurde geöffnet");
};
endpoint.onclose = function() {
	console.log("websocket: wurde geschlossen");
};
endpoint.onmessage = function(evt) {
	console.log("websocket: nachricht wurde empfangen");
	window.location.replace(window.location.href);
};

