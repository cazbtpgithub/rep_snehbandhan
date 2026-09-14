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
@Table(name = "SortlistedProfileUserWithDate")
public class SortlistedProfileUserWithDate {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sortlistId", nullable = false)
	private long sortlistId;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "userModel_id") 
	private UserModel userModel;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "sortlisted_UserModel_id")
	private UserModel sortlistedUserModel;

	@Column(name = "sortlisted_Date")
	private Date sortlistedDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "status_Date")
	private Date statusDate;

	public long getSortlistId() {
		return sortlistId;
	}

	public void setSortlistId(long sortlistId) {
		this.sortlistId = sortlistId;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserModel getSortlistedUserModel() {
		return sortlistedUserModel;
	}

	public void setSortlistedUserModel(UserModel sortlistedUserModel) {
		this.sortlistedUserModel = sortlistedUserModel;
	}

	public Date getSortlistedDate() {
		return sortlistedDate;
	}

	public void setSortlistedDate(Date sortlistedDate) {
		this.sortlistedDate = sortlistedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	
	
}
