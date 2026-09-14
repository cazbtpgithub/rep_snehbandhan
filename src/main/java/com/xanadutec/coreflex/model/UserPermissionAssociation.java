package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "UserPermissionAssociation")
public class UserPermissionAssociation 
{
	@Id
	@GeneratedValue
	@Column(name="ID")
	int id;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	UserModel userModel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PERMISSION_ID")
	PermissionType permissionType;

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

	public PermissionType getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}
	
	
	
	
}
