package edu.cxy.svspcxy.advice;

import edu.cxy.svspcxy.util.WebSocketUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@RestController
@ServerEndpoint("/WebSocketHandler/{userName}")		//表示接受的是STOMP协议提交的数据
public class WebSocketHandler {

	//建立连接
	@OnOpen
	public void openSession(@PathParam("userName")String userName,Session session) {
		// 保存用户-对应的session
		WebSocketUtil.MESSAGEMAP.put(userName, session);
	}

	@OnMessage
	public void onMessage(@PathParam("userName")String userName,String message) {
		// 获取用户对应的session
		Session session = WebSocketUtil.MESSAGEMAP.get(userName);
		// 给该用户发消息
		WebSocketUtil.sendMessage(session, message);
	}

	//离开聊天室
	@OnClose
	public void onClose(@PathParam("userName")String userName,Session session) {
		//将当前用户从map中移除 注销
		WebSocketUtil.MESSAGEMAP.remove(userName);
		//群发消息
		WebSocketUtil.sendMessageToAll("用户:"+userName+"离开聊天室");
		//关闭session
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//连接异常
	@OnError
	public void onError(Session session,Throwable throwable) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}