package com.xanadutec.coreflex.model;


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
@Table(name = "Dealer")
public class Dealer {
	
	

	@Id 
	@GeneratedValue
	@Column(name="id",nullable = false)
	int id;
	
	@Column(name="email",length=30)
	String email;
	@Column(name="mobileNum",length=20)
	String mobileNum;
	@Column(name="branchName",length=30)
	String branchName;

	@Column(name="fullName",length=300)
	String fullName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userModel",nullable=false)
	UserModel userModel;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="mainDealer",nullable=false)
	UserModel mainDealer;
	
	
	@Column(name="dealecode")
	String dealecode;
	@Column(name="discount")
	int discount;
	
	@Column(name="regDate")
	Date regDate;
	
	@Column(name="address")
	String address;
	@Column(name="pincode")
	String pincode;
	@Column(name="officeNo")
	String officeNo;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getDealecode() {
		return dealecode;
	}

	public void setDealecode(String dealecode) {
		this.dealecode = dealecode;
	}

	public UserModel getMainDealer() {
		return mainDealer;
	}

	public void setMainDealer(UserModel mainDealer) {
		this.mainDealer = mainDealer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="status",nullable=true)
	Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	

}
