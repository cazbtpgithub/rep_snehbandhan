package com.xanadutec.coreflex.model;

import java.util.Date;

public class AdminUserModel {
	Long userId;
	String firstName;
	Date registrationDate;
	Date dateOfBirth;
	String mobileNum;
	
	String fullName;

	Kit kit;
	
	String gender;

	Date deleteDate;
	String  deletedBy;
	int deleteId;

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}