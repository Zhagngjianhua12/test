package com.bjsxt.chat;

import com.bjsxt.util.DateUtil;
/**
 * 
 * @author ͷ���ţ�IT�߾���ԭ��
 *
 */
public class MessageWrap {
	/**
	 * ��װ ��¼ 
	 */
	public static Message wrapLogin(String nickName){
		return wrap(ChatType.LOGIN,nickName,"all","��ӭ["+nickName+"]������ͥ");
	}
	
	/**
	 * ��װ �˳�
	 */
	public static Message wrapLogout(String nickName){
		return wrap(ChatType.LOGOUT,nickName,"all","["+nickName+"]�뿪��������");
	}
	
	/**
	 * ��װ Ⱥ��
	 */
	public static Message wrapGroup(String nickName,String content){
		return wrap(ChatType.GROUP,nickName,"all",content);
	}
	/**
	 * ��װ Ⱥ��Ϣ
	 */
	public static Message wrapAdvert(String nickName,String content){
		return wrap(ChatType.ADVERT,"sys",nickName,content);
	}
	/**
	 * ��װ ˽��
	 */
	public static Message wrapSecret(String nickName,String to,String content){
		return wrap(ChatType.SECRET,nickName,to,content);		
	}
	/**
	 * ��װ ����
	 */
	public static Message wrapForbit(String toName){
		return wrap(ChatType.FORBIT,"admin",toName,"forbit");		
	}
	/**
	 * ��װ ���
	 */
	public static Message wrapRid(String toName){
		return wrap(ChatType.RID,"admin",toName,"rid");		
	}
	/**
	 * �ַ�����װ��Message����
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
