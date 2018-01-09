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
 * �ն���ڵ�:
 * 1��ע������ (������)
 * 2���¼����� (�ص�)
 * ǰ�˷���URL: ws://localhost:8080/chat/start
 * �ǵ������̰߳�ȫ�ġ�
 * @author ͷ���ţ�IT�߾���ԭ��
 *
 */
@ServerEndpoint("/start")
public class ChatEndPoint {
	//�洢�����û�
	private static Map<String,ChatEndPoint> members=new ConcurrentHashMap<>();
	private static Gson gson =new Gson();
	//ͨ��
	private Session session;
	//�ǳ�
	private String nickName;
	@OnOpen
	public void open(Session session){
		System.out.println("��������"+session.getId());
		//System.out.println(this.session.getQueryString());
		this.session=session;
		nickName =this.session.getQueryString().split("=")[1];
		members.put(nickName, this);//��ӻ�Ա�б���
		//��֪����������  nickName ������
		broadcast(MessageWrap.wrapLogin(nickName));
		
	}
	@OnClose
	public void close(Session session){	
		//�ӻ�Ա�б����Ƴ�
		members.remove(nickName); 
		//�㲥��������
		broadcast(MessageWrap.wrapLogout(nickName));	
		System.out.println("�ر�����"+session.getId());
	}
	@OnMessage
	public void dealWith(String message){
		//�����Ƿ�Ϊ˽��
		boolean isStart =message.startsWith("@");
		boolean isForbit =message.startsWith("fb");//fb:xt
		boolean isRid =message.startsWith("rd");//rd:xt
		boolean isBoot =message.startsWith("bt");//bt:xt
		int blankIdx =message.indexOf(":");
		String to="all";
		String content =message;
		//˽��
		if(isStart && blankIdx>0){//˽��:�������������� @xxx:...
			to=message.substring(1,blankIdx);
			content=message.substring(blankIdx+1);
			secret(MessageWrap.wrapSecret(nickName,to, content)); 
		}else if(isForbit&&blankIdx>0){//��ֹ
			if(nickName.equals("admin")){//����ԱȨ��
				to =message.substring(blankIdx+1);
				if(null!=members.get(to)){
					sendMessage(members.get(to),gson.toJson(MessageWrap.wrapForbit(to)));
				}
			}			
		}else if(isRid&&blankIdx>0){ //���
			if(nickName.equals("admin")){//����ԱȨ��
				to =message.substring(blankIdx+1);
				if(null!=members.get(to)){
					sendMessage(members.get(to),gson.toJson(MessageWrap.wrapRid(to)));					
				}
			}	
			
		}else if(isBoot&&blankIdx>0){//����			
			if(nickName.equals("admin")){//����ԱȨ��
				to =message.substring(blankIdx+1);
				ChatEndPoint toEnd=members.get(to);
				members.remove(to); //�Ƴ�
				try {
					toEnd.session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				broadcast(MessageWrap.wrapGroup("admin",to+"������"));	
				
			}
		}else{//Ⱥ��			
			broadcast(MessageWrap.wrapGroup(nickName,content));	
		}
	}
	
	/**
	 * ˽��
	 * @param message
	 */
	private void secret(Message message){
		//���л�Ա
		message.setMembers(members.keySet());
		//���Ͷ���
		ChatEndPoint from=members.get(message.getFrom());
		ChatEndPoint to=members.get(message.getTo());
		//��������
		String msg =gson.toJson(message);
		//�����Լ�
		sendMessage(from,msg);
		if(null!=to){
			//��������
			sendMessage(to,msg);			
		}else{
			//���Ѳ����� ,ϵͳ��֪Ŀ�����Ѳ�������Ϣ			
			message =MessageWrap.wrapAdvert(message.getFrom(), message.getTo()+"�û������ڻ����Ѿ��뿪....");
			sendMessage(from,gson.toJson(message));
		}
	}
	
	/*
	 *������Ϣ(json��)
	 */
	private void broadcast(Message message){
		//���л�Ա
		message.setMembers(members.keySet()); 
		String jsonStr =gson.toJson(message);
		for(ChatEndPoint client:members.values()){			
			sendMessage(client,jsonStr);
		}
		
	}
	//������Ϣ
	private void sendMessage(ChatEndPoint client,String jsonStr){
		try {
			client.session.getBasicRemote().sendText(jsonStr);
		} catch (IOException e) {
			//�ӻ�Ա�б����Ƴ�
			members.remove(client.nickName); 
			//�ر�
			try {
				this.session.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
			//��֪����������  nickName �뿪��			
			broadcast(MessageWrap.wrapLogout(client.nickName));	
		}
	}

}
