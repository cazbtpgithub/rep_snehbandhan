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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="UserModelImagesName")
public class UserModelImagesName{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userProfileImageId", nullable = false)
	private Integer userProfileImageId;

	@Column(name = "imageName")
	private String imageNames;
	
	
	@Column(name = "kundaliImage")
	private String kundaliImage;
	

	public String getKundaliImage() {
		return kundaliImage;
	}

	public void setKundaliImage(String kundaliImage) {
		this.kundaliImage = kundaliImage;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="userid")
	private UserModel userModel;

	public Integer getUserProfileImageId() {
		return userProfileImageId;
	}

	public void setUserProfileImageId(Integer userProfileImageId) {
		this.userProfileImageId = userProfileImageId;
	}

	public String getImageNames() {
		return imageNames;
	}

	public void setImageNames(String imageNames) {
		this.imageNames = imageNames;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
	
}
