package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;
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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "VisitedProfileUserWithDate")
public class VisitedProfileUserWithDate {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "visitorid", nullable = false)
	private Integer visitorid;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "userModel_id") 
	private UserModel userModel;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "visitedUserModel_id")
	private UserModel visitedUserModel;

	@Column(name = "profileViewedDate")
	private Date profileViewedDate;

	public Integer getVisitorid() {
		return visitorid;
	}

	public void setVisitorid(Integer visitorid) {
		this.visitorid = visitorid;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserModel getVisitedUserModel() {
		return visitedUserModel;
	}

	public void setVisitedUserModel(UserModel visitedUserModel) {
		this.visitedUserModel = visitedUserModel;
	}

	public Date getProfileViewedDate() {
		return profileViewedDate;
	}

	public void setProfileViewedDate(Date profileViewedDate) {
		this.profileViewedDate = profileViewedDate;
	}

	
}
