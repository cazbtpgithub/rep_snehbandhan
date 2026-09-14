package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="USER")
public class UserModel{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="USER_ROLL")
	private RoleModel userRoll;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="Kit_id")
	private Kit kit;

	@Column(name = "Mobile_No")
	private String mobileNo;
	
	@Column(name = "Full_Name")
	private String firstName;    
	
	@Column(name = "Device_Token")
	private String deviceToken;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleModel getUserRoll() {
		return userRoll;
	}

	public void setUserRoll(RoleModel userRoll) {
		this.userRoll = userRoll;
	}


	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

}
