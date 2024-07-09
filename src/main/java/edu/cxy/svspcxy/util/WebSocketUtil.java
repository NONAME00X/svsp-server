package edu.cxy.svspcxy.util;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtil {
	//HashMap：不支持多线程，并发情况下线程不安全
	public static final Map<String, Session> MESSAGEMAP = new ConcurrentHashMap<>();

	//发送消息给客户端
	public static void sendMessage(Session session,String message) {
		if (session!=null) {
			final RemoteEndpoint.Basic basic = session.getBasicRemote();
			if (basic!=null) {
				try {
					basic.sendText(message);//发送消息回客户端
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//将消息给所有聊天室的人
	//循环发送
	public static void sendMessageToAll(String message) {
		MESSAGEMAP.forEach((sessionId,session)->sendMessage(session, message));
	}
}
