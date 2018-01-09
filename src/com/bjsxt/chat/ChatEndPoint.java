package com.bjsxt.chat;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
/**
 * 终端入口点:
 * 1、注解配置 (零配置)
 * 2、事件驱动 (回调)
 * 前端访问URL: ws://localhost:8080/chat/start
 * 非单例，线程安全的。
 * @author 头条号：IT蛇精病原创
 *
 */
@ServerEndpoint("/start")
public class ChatEndPoint {
	//存储所有用户
	private static Map<String,ChatEndPoint> members=new ConcurrentHashMap<>();
	private static Gson gson =new Gson();
	//通道
	private Session session;
	//昵称
	private String nickName;
	@OnOpen
	public void open(Session session){
		System.out.println("建立连接"+session.getId());
		//System.out.println(this.session.getQueryString());
		this.session=session;
		nickName =this.session.getQueryString().split("=")[1];
		members.put(nickName, this);//添加会员列表中
		//告知在线所有人  nickName 加入了
		broadcast(MessageWrap.wrapLogin(nickName));
		
	}
	@OnClose
	public void close(Session session){	
		//从会员列表中移除
		members.remove(nickName); 
		//广播给所有人
		broadcast(MessageWrap.wrapLogout(nickName));	
		System.out.println("关闭连接"+session.getId());
	}
	@OnMessage
	public void dealWith(String message){
		//分析是否为私聊
		boolean isStart =message.startsWith("@");
		boolean isForbit =message.startsWith("fb");//fb:xt
		boolean isRid =message.startsWith("rd");//rd:xt
		boolean isBoot =message.startsWith("bt");//bt:xt
		int blankIdx =message.indexOf(":");
		String to="all";
		String content =message;
		//私聊
		if(isStart && blankIdx>0){//私聊:发给来的数据是 @xxx:...
			to=message.substring(1,blankIdx);
			content=message.substring(blankIdx+1);
			secret(MessageWrap.wrapSecret(nickName,to, content)); 
		}else if(isForbit&&blankIdx>0){//禁止
			if(nickName.equals("admin")){//管理员权限
				to =message.substring(blankIdx+1);
				if(null!=members.get(to)){
					sendMessage(members.get(to),gson.toJson(MessageWrap.wrapForbit(to)));
				}
			}			
		}else if(isRid&&blankIdx>0){ //解禁
			if(nickName.equals("admin")){//管理员权限
				to =message.substring(blankIdx+1);
				if(null!=members.get(to)){
					sendMessage(members.get(to),gson.toJson(MessageWrap.wrapRid(to)));					
				}
			}	
			
		}else if(isBoot&&blankIdx>0){//踢人			
			if(nickName.equals("admin")){//管理员权限
				to =message.substring(blankIdx+1);
				ChatEndPoint toEnd=members.get(to);
				members.remove(to); //移除
				try {
					toEnd.session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				broadcast(MessageWrap.wrapGroup("admin",to+"被踢了"));	
				
			}
		}else{//群聊			
			broadcast(MessageWrap.wrapGroup(nickName,content));	
		}
	}
	
	/**
	 * 私聊
	 * @param message
	 */
	private void secret(Message message){
		//所有会员
		message.setMembers(members.keySet());
		//发送对象
		ChatEndPoint from=members.get(message.getFrom());
		ChatEndPoint to=members.get(message.getTo());
		//发送内容
		String msg =gson.toJson(message);
		//发给自己
		sendMessage(from,msg);
		if(null!=to){
			//发给朋友
			sendMessage(to,msg);			
		}else{
			//朋友不存在 ,系统告知目标朋友不存在消息			
			message =MessageWrap.wrapAdvert(message.getFrom(), message.getTo()+"用户不存在或者已经离开....");
			sendMessage(from,gson.toJson(message));
		}
	}
	
	/*
	 *发送信息(json串)
	 */
	private void broadcast(Message message){
		//所有会员
		message.setMembers(members.keySet()); 
		String jsonStr =gson.toJson(message);
		for(ChatEndPoint client:members.values()){			
			sendMessage(client,jsonStr);
		}
		
	}
	//发送消息
	private void sendMessage(ChatEndPoint client,String jsonStr){
		try {
			client.session.getBasicRemote().sendText(jsonStr);
		} catch (IOException e) {
			//从会员列表中移除
			members.remove(client.nickName); 
			//关闭
			try {
				this.session.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
			//告知在线所有人  nickName 离开了			
			broadcast(MessageWrap.wrapLogout(client.nickName));	
		}
	}

}
