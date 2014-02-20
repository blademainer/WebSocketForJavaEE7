var URL = "ws://localhost:8080/WebSocketForJavaEE7/websocket/web-client";
var websocket;

$(document).ready(function() {
	connect();
});

function connect() {
	websocket = new WebSocket(URL);
	websocket.onopen = onOpen;
	websocket.onmessage = onMessage;
	websocket.onclose = onChannelClosed;

}
function sendMessage() {
	websocket.send($("#message").val());
}
function onOpen() {
	updateStatus("connected")
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