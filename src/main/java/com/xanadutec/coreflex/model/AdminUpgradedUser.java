package com.xanadutec.coreflex.model;

import java.util.Date;

public class AdminUpgradedUser {
	
	Long userId;
	String name;
	String kit;
	String packageName;
	Date registrationDate;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKit() {
		return kit;
	}
	public void setKit(String kit) {
		this.kit = kit;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
