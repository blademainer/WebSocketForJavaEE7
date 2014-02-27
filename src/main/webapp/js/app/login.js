function doLogin(){
	webSocketVo.appId="login";
	content.userName="admin";
	content.password="111";
	webSocketVo.content = content;
	
//	var inventoryJSONTextAgain = JSON.stringify(webSocketVo, null, 3);
	var inventoryJSONTextAgain = JSON.stringify(webSocketVo);
	var obj = JSON.parse(inventoryJSONTextAgain);
	console.log(inventoryJSONTextAgain);
	console.log(obj);
	
	var str = "{'appId': 'login'}";//,"content": {"userName": "admin","password": "111"}
	sendMessage(inventoryJSONTextAgain);
//	sendMessage("testString");
//	sendMessage(str);
}