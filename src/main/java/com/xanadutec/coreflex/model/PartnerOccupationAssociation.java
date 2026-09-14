package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity(name="PartnerOccupationAssociation")
public class PartnerOccupationAssociation {

	@Id
	@GeneratedValue
	@Column(name="ID")
	int id;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	UserModel userModel;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="occupationCategory_id")
	private OccupationCategory occupationCategory;


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


	public OccupationCategory getOccupationCategory() {
		return occupationCategory;
	}


	public void setOccupationCategory(OccupationCategory occupationCategory) {
		this.occupationCategory = occupationCategory;
	}
	
	
	
	
}
