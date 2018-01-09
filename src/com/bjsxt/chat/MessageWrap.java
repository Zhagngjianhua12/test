package com.bjsxt.chat;

import com.bjsxt.util.DateUtil;
/**
 * 
 * @author 头条号：IT蛇精病原创
 *
 */
public class MessageWrap {
	/**
	 * 封装 登录 
	 */
	public static Message wrapLogin(String nickName){
		return wrap(ChatType.LOGIN,nickName,"all","欢迎["+nickName+"]加入大家庭");
	}
	
	/**
	 * 封装 退出
	 */
	public static Message wrapLogout(String nickName){
		return wrap(ChatType.LOGOUT,nickName,"all","["+nickName+"]离开了聊天室");
	}
	
	/**
	 * 封装 群聊
	 */
	public static Message wrapGroup(String nickName,String content){
		return wrap(ChatType.GROUP,nickName,"all",content);
	}
	/**
	 * 封装 群信息
	 */
	public static Message wrapAdvert(String nickName,String content){
		return wrap(ChatType.ADVERT,"sys",nickName,content);
	}
	/**
	 * 封装 私聊
	 */
	public static Message wrapSecret(String nickName,String to,String content){
		return wrap(ChatType.SECRET,nickName,to,content);		
	}
	/**
	 * 封装 禁言
	 */
	public static Message wrapForbit(String toName){
		return wrap(ChatType.FORBIT,"admin",toName,"forbit");		
	}
	/**
	 * 封装 解禁
	 */
	public static Message wrapRid(String toName){
		return wrap(ChatType.RID,"admin",toName,"rid");		
	}
	/**
	 * 字符串封装成Message对象
	 * @param type
	 * @param nickName
	 * @param to
	 * @param content
	 * @return
	 */
	private static Message  wrap(String type,String nickName,String to,String content){
		Message m =new Message();
		m.setType(type);
		m.setFrom(nickName);
		m.setTo(to);
		m.setContent(content);
		m.setNowtime(DateUtil.formatStrOnlyHms(System.currentTimeMillis()));	
		return m;
	}
}
