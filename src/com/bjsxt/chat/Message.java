package com.bjsxt.chat;

import java.util.Set;

/**
 * ��ϢPO
 * {type:'login',from:'sys',to:'all',content:'xxx ������������.',date:'2015-09-22 13:02:20'}
 * @author ͷ���ţ�IT�߾���ԭ��
 *
 */
public class Message {
	//����
	private String type;
	//������
	private String from;
	//������
	private String to;
	//����
	private String content;
	//����
	private String nowtime;
	//ͷ��
	private String avatar;
	//������
	private  Set<String>  members;
	public Message() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}

	public Set<String> getMembers() {
		return members;
	}

	public void setMembers(Set<String> members) {
		this.members = members;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}	
}
