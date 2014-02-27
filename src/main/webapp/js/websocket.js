var sessionId = "asfsfsf";
var URL = "ws://localhost:8080/WebSocketForJavaEE7/websocket/" + sessionId;
//var URL = "ws://localhost:8081";
var websocket;
var webSocketVo = {};
var content = {};
var connected = false;


$(document).ready(function() {
	connect();
});

function connect() {
	websocket = new WebSocket(URL);
	websocket.onopen = onOpen;
	websocket.onmessage = onMessage;
	websocket.onclose = onChannelClosed;

}

function sendMessage(message){
	console.log("sendMessage: " + message);
	websocket.send(message);
}

function doSendMessage() {
	//websocket.send($("#message").val());
	doLogin();
}
function onOpen() {
	connected = true;
	updateStatus("connected");
}
function onChannelClosed() {
	updateStatus("onChannelClosed.");
	socket = null;
}
function onMessage(evnt) {
	console.log("onMessage, evnt.data ======= " + evnt.data);
	if (typeof evnt.data == "string") {
		$("#received_messages").append(evnt.data + "<br>");
	}
}
function onError(evnt) {
	console.log('ERROR: ' + evnt.data);
}
function updateStatus(status) {
	console.log(status);
	$("#status").removeClass(function(index, css) {
		return (css.match(/\blabel-\S+/g) || []).join(' ')
	});
	$("#status").text(status).addClass("label-success");
}