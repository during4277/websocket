<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Insert title here</title>
<script>
	var wsocket;
	function connect() {
		wsocket = new WebSocket("ws://192.168.0.107:88/icia/chat"); //Html5 웹 소켓 생성
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
		wsocket.onopen = onOpen;  
	}
	function disconnect() {
		wsocket.close();
	}
	function onOpen(evt) {
		appendMessage("연결되었습니다");
	}
	function onMessage(evt) {
		alert("알림이 도착했어요")
		console.log(evt);
		var data = evt.data;
		if (data.substring(0, 4) == "msg:")
			appendMessage(data.substring(4));
	}
	function onClose(evt) {
		alert("연결 종료");
	}
	function send() {
		var nickname = $("#nickname").val();
		var msg = $("#message").val();
		wsocket.send("msg:" + nickname + ":" + msg); //websocketmessage로 전송
		$("#message").val("");
	}
	function appendMessage(msg) {
		$("#chatMessageArea").append(msg + "<br>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}
	//$(document).ready(function() {
		$("#message").on("keypress", function(evt) {
			var keycode = evt.keyCode;
			if (keycode == 13)
				send();
		});
		$("#sendBtn").click(function() { send();})
		$("#enterBtn").click(function() {connect();})
		$("#exitBtn").click(function() { disconnect();})
	//});
</script>
<title>Insert title here</title>
<style>
#chatArea {
	width: 200px;
	height: 100px; 
	overflow-y : auto;
	border: 1px solid black;
	overflow-y: auto;
}
</style>
</head>
<body>
	이름:
	<input type="text" id="nickname">
	<input type="button" id="enterBtn" onclick="connect()"value="입장">
	<input type="button" id="exitBtn" onclick="disconnect()" value="나가기">
	<h1>대화 영역</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	<input type="text" id="message">
	<input type="button" id="sendBtn" onclick="send()" value="전달">
</body>
