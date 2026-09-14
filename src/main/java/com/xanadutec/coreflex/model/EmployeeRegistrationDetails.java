package com.xanadutec.coreflex.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name ="EmployeeRegistrationDetails")
public class EmployeeRegistrationDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="emp_id")
	private int emp_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="USER_MODEL")
	private UserModel userModel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="STATUS")
	private Status status;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name ="userName")
	private String userName;
	
	@Column(name ="password")
	private String password;
	
	@Column(name ="accountNo")
	private String accountNo;

	@Column(name ="branchName")
	private String branchName;
	
	@Column(name ="ifcCode")
	private String ifcCode;
	
	@Column(name ="bankName")
	private String bankName;
	
	@Column(name ="designation")
	private String designation;
	
	@Column(name ="experienceDetails")
	private String experienceDetails;
	
	@Column(name ="totalExperience")
	private String totalExperience;
	
	@Column(name ="currentSalary")
	private double currentSalary;
	
	@Column(name ="previousSalary")
	private double previousSalary;
	
	@Column(name ="mobileNo")
	private String mobileNo;
	
	@Column(name ="alternateMobileNo")
	private String alternateMobileNo;
	
	@Column(name ="email")
	private String email;
	
	@Column(name ="address")
	private String address;
	
	@Column(name ="city")
	private String city;
	
	@Column(name ="dateOfBirth ")
	private Date dateOfBirth;
	
	@Column(name = "RegistrationDate")
	private Date registrationDate;
	


	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfcCode() {
		return ifcCode;
	}

	public void setIfcCode(String ifcCode) {
		this.ifcCode = ifcCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getExperienceDetails() {
		return experienceDetails;
	}

	public void setExperienceDetails(String experienceDetails) {
		this.experienceDetails = experienceDetails;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public double getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(double currentSalary) {
		this.currentSalary = currentSalary;
	}

	public double getPreviousSalary() {
		return previousSalary;
	}

	public void setPreviousSalary(double previousSalary) {
		this.previousSalary = previousSalary;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
    
	
	
}
