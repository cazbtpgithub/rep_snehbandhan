package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="PermissionType")
public class PermissionType 
{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name ="id")
	private int id;

	@Column( name ="permissionType")
	private String permissionType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}



}
