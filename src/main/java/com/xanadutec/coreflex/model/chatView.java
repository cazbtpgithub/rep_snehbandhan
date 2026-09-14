package com.xanadutec.coreflex.model;

import java.util.Date;

public class chatView {
	
	private String chatId;
	private String chatText;
	private Date chatdate;
	private String userModelTo;
	private String userModelFrom;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getChatText() {
		return chatText;
	}
	public void setChatText(String chatText) {
		this.chatText = chatText;
	}
	public Date getChatdate() {
		return chatdate;
	}
	public void setChatdate(Date chatdate) {
		this.chatdate = chatdate;
	}
	public String getUserModelTo() {
		return userModelTo;
	}
	public void setUserModelTo(String userModelTo) {
		this.userModelTo = userModelTo;
	}
	public String getUserModelFrom() {
		return userModelFrom;
	}
	public void setUserModelFrom(String userModelFrom) {
		this.userModelFrom = userModelFrom;
	}

}
