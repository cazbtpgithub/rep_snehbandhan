package com.xanadutec.coreflex.ReportModel;

import java.util.Date;

public class MemberReportModel 
{
  int srNo;
  String userName;
  String mobileNumber;
  String emailId;  
  String gender;
  String kitId;
  String  packageName;
  Date activationDate;
  Date expireDate;
  Long userId;
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public int getSrNo() {
	return srNo;
}
public void setSrNo(int srNo) {
	this.srNo = srNo;
}

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getKitId() {
	return kitId;
}
public void setKitId(String kitId) {
	this.kitId = kitId;
}
public Date getActivationDate() {
	return activationDate;
}
public void setActivationDate(Date activationDate) {
	this.activationDate = activationDate;
}
public Date getExpireDate() {
	return expireDate;
}
public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
}
public String getPackageName() {
	return packageName;
}
public void setPackageName(String packageName) {
	this.packageName = packageName;
}
  
}
