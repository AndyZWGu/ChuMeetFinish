package server;

import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyWebSocketServer/{memID}/{receiver}")
public class MyWebSocketServer {

	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(@PathParam("memID") String name, @PathParam("receiver") int room, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		//connectedSessions.add(receiverSession);
		String text = String.format("Session ID = %s, connected; name = %s; room = %s", 
				userSession.getId(), name, room);
		//String text2 = String.format("Session ID = %s, connected; name = %s; room = %s", 
		//		receiverSession.getId(), name, room);
		System.out.println(text);
		//System.out.println(text2);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) throws IOException {
		//發送給所有人
//		for (Session session : connectedSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//		}
		//私聊
		for (Session session : connectedSessions) {
			if (session.isOpen()){
					System.out.println("當前session參數"+session.getPathParameters());
					//System.out.println(session.getRequestParameterMap().get("receiver"));
					Map<String,String> user = session.getPathParameters();
					Gson gson = new Gson();
					ChatMem chatMem = gson.fromJson(message, ChatMem.class);
//					System.out.println(chatMem.getMessage());
//					System.out.println(chatMem.getReceiverID());
//					System.out.println(chatMem.getUserID());
//					System.out.println(chatMem.getUserName());
					String receiverID = chatMem.getReceiverID();
					String userID = chatMem.getUserID();
					String memID =session.getPathParameters().get("memID");
					System.out.println("session擁有者"+memID);
					System.out.println("拿訊息的人"+receiverID);
					String roomName = chatMem.getUserName();
					//收訊息人拿到訊息
					if(!"club".equals(roomName.trim())&&memID.trim().equals(receiverID.trim())){
						session.getAsyncRemote().sendText(message);
						System.out.println("私人Message received: " + message);
					}
					
					
					//receiverSession.getAsyncRemote().sendText(message);
			}
		}
		
		
		// 社團聊
		for (Session session : connectedSessions) {
			if (session.isOpen()){
					System.out.println("當前session參數"+session.getPathParameters());
					//System.out.println(session.getRequestParameterMap().get("receiver"));
					Map<String,String> user = session.getPathParameters();
					Gson gson = new Gson();
					ChatMem chatMem = gson.fromJson(message, ChatMem.class);
//					System.out.println(chatMem.getMessage());
//					System.out.println(chatMem.getReceiverID());
//					System.out.println(chatMem.getUserID());
//					System.out.println(chatMem.getUserName());
					String receiverID = chatMem.getReceiverID();
					String userID = chatMem.getUserID();
					String memID =session.getPathParameters().get("memID");
					String userName =session.getPathParameters().get("userName");
					System.out.println("session擁有者"+memID);
					System.out.println("拿訊息的人"+receiverID);
					String roomName = chatMem.getUserName();
					//收訊息人拿到訊息
					if("club".equals(roomName.trim())&&!(receiverID.trim().equals(memID))){
						session.getAsyncRemote().sendText(message);
						System.out.println("社團Message received: " + message);
					}
					
					
					//receiverSession.getAsyncRemote().sendText(message);
			}
			
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}
}
