package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="branch")
public class BranchModel {
	
	@Id
	@GeneratedValue
	@Column(name ="branchId")
	int branchId ;
	
	@Column(name ="branchName")
	String branchName;
	
	@Column(name ="branchAdd")
	String branchAddress;
	
	@Column(name ="latitude")
	double latitude;
	
	@Column(name ="longitude")
	double longitude;
	
	@Column(name = "contactNo")
	String contactNo;
	
	@Column(name = "emailId")
	String emailId;
	
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
    
}
