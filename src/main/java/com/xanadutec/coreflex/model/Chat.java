package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
	
	

	@Id 
	@GeneratedValue
	@Column(name="chatId",nullable = false)
	int chatId;
	
	@Column(name="chat_text",length=2000)
	String chatText;
	
	@Column(name="dateofchat")
	Date chatdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userFrom",nullable=false)
	UserModel userModelFrom;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userTo",nullable=false)
	UserModel userModelTo;

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
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

	public UserModel getUserModelFrom() {
		return userModelFrom;
	}

	public void setUserModelFrom(UserModel userModelFrom) {
		this.userModelFrom = userModelFrom;
	}

	public UserModel getUserModelTo() {
		return userModelTo;
	}

	public void setUserModelTo(UserModel userModelTo) {
		this.userModelTo = userModelTo;
	}
	

	

}
