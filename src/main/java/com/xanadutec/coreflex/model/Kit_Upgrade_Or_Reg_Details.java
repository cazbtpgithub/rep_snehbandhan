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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Kit_Upgrade_Or_Reg_Details")
public class Kit_Upgrade_Or_Reg_Details {

	@Id
	@GeneratedValue
	@Column(name = "kit_upgrade_Or_reg_det_Id")
	long kit_upgrade_Or_reg_det_Id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="userModel_id",nullable=false)
	private UserModel userModel;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="Kit_id",nullable=false)
	private Kit Kit_id;
	
	@Column(name = "no_of_msg")
	int no_of_msg;
	
	@Column(name = "no_of_email")
	int no_of_email;

	@Column(name = "no_of_profile")
	int no_of_profile;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "registrationDate")
	private Date registrationDate;
	
	@Column(name = "expiryDate")
	private Date expiryDate;

	public long getKit_upgrade_Or_reg_det_Id() {
		return kit_upgrade_Or_reg_det_Id;
	}

	public void setKit_upgrade_Or_reg_det_Id(long kit_upgrade_Or_reg_det_Id) {
		this.kit_upgrade_Or_reg_det_Id = kit_upgrade_Or_reg_det_Id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public Kit getKit_id() {
		return Kit_id;
	}

	public void setKit_id(Kit kit_id) {
		Kit_id = kit_id;
	}

	public int getNo_of_msg() {
		return no_of_msg;
	}

	public void setNo_of_msg(int no_of_msg) {
		this.no_of_msg = no_of_msg;
	}

	public int getNo_of_email() {
		return no_of_email;
	}

	public void setNo_of_email(int no_of_email) {
		this.no_of_email = no_of_email;
	}

	public int getNo_of_profile() {
		return no_of_profile;
	}

	public void setNo_of_profile(int no_of_profile) {
		this.no_of_profile = no_of_profile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	
}
