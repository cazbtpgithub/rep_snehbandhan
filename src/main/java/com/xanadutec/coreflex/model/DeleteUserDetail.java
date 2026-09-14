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
@Table(name = "DeleteUserDetail")
public class DeleteUserDetail {
	

	@Id
	@GeneratedValue
	@Column(name="id",nullable = false)
	Integer id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userModel",nullable=false)
	UserModel userModel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="deletedBy",nullable=false)
	UserModel deletedBy;
	
	Date deleteDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserModel getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(UserModel deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	
	
	
	
	

}
