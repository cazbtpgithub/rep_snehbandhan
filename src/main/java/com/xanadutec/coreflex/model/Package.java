package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Package")
public class Package {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "package_id", nullable = false)
	private Integer package_id;

	@Column(name = "package_Name")
	private String package_Name;
	
	      
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "no_of_profiles")
	private String no_of_profiles;
	
	@Column(name = "no_of_email")
	private String no_of_email;
	
	@Column(name = "no_of_msg")
	private String no_of_msg;
	
	@Column(name = "no_of_days")
	private String no_of_days;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false)
	Status status_id;

	public Integer getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Integer package_id) {
		this.package_id = package_id;
	}

	public String getPackage_Name() {
		return package_Name;
	}

	public void setPackage_Name(String package_Name) {
		this.package_Name = package_Name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Status getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Status status_id) {
		this.status_id = status_id;
	}

	public String getNo_of_profiles() {
		return no_of_profiles;
	}

	public void setNo_of_profiles(String no_of_profiles) {
		this.no_of_profiles = no_of_profiles;
	}

	public String getNo_of_email() {
		return no_of_email;
	}

	public void setNo_of_email(String no_of_email) {
		this.no_of_email = no_of_email;
	}

	public String getNo_of_msg() {
		return no_of_msg;
	}

	public void setNo_of_msg(String no_of_msg) {
		this.no_of_msg = no_of_msg;
	}

	public String getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}

	
}
