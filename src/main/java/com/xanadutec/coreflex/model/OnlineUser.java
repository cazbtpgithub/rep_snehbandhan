package com.xanadutec.coreflex.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="onlineuser")
public class OnlineUser {

	@Id
	@GeneratedValue
	@Column(name="id",nullable = false)
	int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userModel",nullable=false)
	UserModel userModel;
	@Column(name="loginDate")
	Date loginDate;
	@Column(name="sex")
	String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
}
