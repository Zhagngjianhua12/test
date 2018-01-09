package com.bjsxt.chat;

import java.util.Set;

/**
 * 消息PO
 * {type:'login',from:'sys',to:'all',content:'xxx 加入了聊天室.',date:'2015-09-22 13:02:20'}
 * @author 头条号：IT蛇精病原创
 *
 */
public class Message {
	//类型
	private String type;
	//来自于
	private String from;
	//发送至
	private String to;
	//内容
	private String content;
	//日期
	private String nowtime;
	//头像
	private String avatar;
	//所有人
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
