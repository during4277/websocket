package com.board.icia.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
//import org.springframework.web.socket.*;
//import org.springframework.web.socket.handler.*;

//import com.board.icia.socket.WebSocketHandler;

public class ChatSocketHandler extends TextWebSocketHandler {
	private Map<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
	//String:채팅방이름 , ArrayList<String>:참여세션아이디(session.getId())  , 예,방이름:1 2 3
	private Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	//session으로 list에 id와 메시지가 저장되는데 id값을 얻어서 ArrayList에 저장하고 map에 저장한다.
	private List<WebSocketSession> list = new Vector<WebSocketSession>();
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
		System.out.println("remove="+session);
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		System.out.println("add="+session);
		
	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		for(WebSocketSession s:list) {
			logger.info(message+"");
			s.sendMessage(message);
			System.out.println("확인:"+s.getId());
		}
	}
}
