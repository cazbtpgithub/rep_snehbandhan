package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="smsinfo")
public class SmsInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int Id;
	@Column(name="smsUserName")
	String userName;
	@Column(name="smspassword")
	String password;
	@Column(name="senderid")
	String senderid;
	@Column(name="channel")
	String channel;
	@Column(name="flashsms")
	String flashsms;
	@Column(name="DCS")
	String DCS;
	@Column(name="route")
	String route;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getFlashsms() {
		return flashsms;
	}
	public void setFlashsms(String flashsms) {
		this.flashsms = flashsms;
	}
	public String getDCS() {
		return DCS;
	}
	public void setDCS(String dCS) {
		DCS = dCS;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	
	
	
	
	
	//        
	

}
