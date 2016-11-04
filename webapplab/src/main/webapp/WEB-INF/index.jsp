<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>CountDownApp</title>
</head>
<body>
	Bonjour ${user} <br />
	<input type="text" id="message" value=""><br />
	


	<input type="hidden" id="userid" value="${user }">
	<script type="text/javascript">
		var userid = document.getElementById("userid").value;
		var socket = new WebSocket("ws://"+window.location.host+window.location.pathname+"websocket/"+userid);
		socket.onmessage = function(msg){
			document.getElementById("message").value=msg.data;
		};
	</script>
</body>
</html>