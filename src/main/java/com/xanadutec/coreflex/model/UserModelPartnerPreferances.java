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

@Entity
@Table(name="UserModelPartnerPreferances")
public class UserModelPartnerPreferances{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer userDetailsId;
	
	@Column(name = "materialStatus")
	private String materialStatus;
	
	@Column(name = "physicalStatus")
	private String physicalStatus;
	
	@Column(name = "heightFrom")
	private Double heightFrom;
	
	@Column(name = "heightTo")
	private Double heightTo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="userModel_id")
	private UserModel userModel;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="Country_id")
	private Country country;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="state_Country_id")
	private State_Country state_Country;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="districts_id")
	private State_Districts districts;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="religion_id")
	private Religion religion ;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="caste_id")
	private Caste caste;
	
	@Column(name = "employeedIn")
	private String employeedIn;

	@Column(name = "incomeType")
	private String incomeType;
	
	
	@Column(name = "incomeAmount")
	private Long incomeAmount;    

	@Column(name = "familyClass")
	private String familyClass;
	
	@Column(name = "familyType")
	private String familyType;
	
	@Column(name = "familyValues")
	private String familyValues;

	@Column(name = "eating")
	private String eating;
	
	@Column(name = "drink")
	private String drink;
	
	@Column(name = "smoke")
	private String smoke;
	
	@Column(name = "bodyType")
	private String bodyType;
	
	@Column(name = "complexion")
	private String complexion;

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public Double getHeightFrom() {
		return heightFrom;
	}

	public void setHeightFrom(Double heightFrom) {
		this.heightFrom = heightFrom;
	}

	public Double getHeightTo() {
		return heightTo;
	}

	public void setHeightTo(Double heightTo) {
		this.heightTo = heightTo;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State_Country getState_Country() {
		return state_Country;
	}

	public void setState_Country(State_Country state_Country) {
		this.state_Country = state_Country;
	}

	public State_Districts getDistricts() {
		return districts;
	}

	public void setDistricts(State_Districts districts) {
		this.districts = districts;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Caste getCaste() {
		return caste;
	}

	public void setCaste(Caste caste) {
		this.caste = caste;
	}

	public String getEmployeedIn() {
		return employeedIn;
	}

	public void setEmployeedIn(String employeedIn) {
		this.employeedIn = employeedIn;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public Long getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Long incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public String getFamilyClass() {
		return familyClass;
	}

	public void setFamilyClass(String familyClass) {
		this.familyClass = familyClass;
	}

	public String getFamilyType() {
		return familyType;
	}

	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}

	public String getFamilyValues() {
		return familyValues;
	}

	public void setFamilyValues(String familyValues) {
		this.familyValues = familyValues;
	}

	public String getEating() {
		return eating;
	}

	public void setEating(String eating) {
		this.eating = eating;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getComplexion() {
		return complexion;
	}

	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getMaterialStatus() {
		return materialStatus;
	}

	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}

	public String getPhysicalStatus() {
		return physicalStatus;
	}

	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
	}
	
	
}
