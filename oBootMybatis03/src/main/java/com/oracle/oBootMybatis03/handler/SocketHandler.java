package com.oracle.oBootMybatis03.handler;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
	// 웹소켓 세션을 담아둘 맵 
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	// 웹소켓 세션 ID와 Member를 담아둘 맵
	HashMap<String, String> sessionUserMap = new HashMap<>();
	
	// 메소드는 메시지를 수신하면 실행
	public void handelTextMessage(WebSocketSession session, TextMessage message) {
		// 메시지 발송
		String msg = message.getPayload();
		System.out.println("SocketHandler handelTextMessage msg->" + msg);
		
		JSONObject jsonObj = jsonToObjectParser(msg);
		// type을 Get하여 분기
		String msgType = (String) jsonObj.get("type");
		System.out.println("SocketHandler handelTextMessage msgType->" + msgType);
		
	}
	
	// 웹소켓 연결이 되면 동작
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("SocketHandler afterConnectionEstablished start");
		// 웹소켓 연결이 되면 동작
		super.afterConnectionEstablished(session);
	}
	
	// 웹소켓이 종료되면 동작
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println("SocketHandler afterConnectionClosed start");
		// 웹소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	// handleTestMessage메소드에 JSON파일이 들어오면 파싱해주는 함수를 추가
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

}
