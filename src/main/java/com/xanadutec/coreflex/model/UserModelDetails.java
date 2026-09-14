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
@Table(name="UserModelDetails")
public class UserModelDetails{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer userDetailsId;
	
	@Column(name = "aboutMe",length=3000)
	private String aboutMe;
	
	@Column(name = "materialStatus")
	private String materialStatus;
	
	@Column(name = "physicalStatus")
	private String physicalStatus;
	
	@Column(name = "height")
	private Double height;
	
	@Column(name = "weight")
	private Integer weight;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="userModel_id",nullable=false)
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
	@JoinColumn(name="subDistricts_id")
	private District_SubDistricts subDistricts;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="caste_id")
	private Caste caste;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="education_id")
	private Education education;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="occupation_id")
	private Occupation occupation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="status",nullable=true)
	private Status status;
	public Status getStatus() {
		return status;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="dealer")
	private Dealer dealer;

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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
	
	@Column(name = "registrationDate")
	private Date registrationDate;
	
	@Column(name = "profileUpdateDate")
	private Date profileUpdateDate;
	
	@Column(name = "lastLoginDate")
	private Date lastLoginDate;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobileNum")
	private String mobileNum;
	
	@Column(name = "profileCreatedFor")
	private String profileCreatedFor;
	
	@Column(name = "landmark",length=200)
	private String landmark;
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="empId")
	private EmployeeRegistrationDetails empId;
	

	public EmployeeRegistrationDetails getEmpId() {
		return empId;
	}

	public void setEmpId(EmployeeRegistrationDetails empId) {
		this.empId = empId;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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

	public District_SubDistricts getSubDistricts() {
		return subDistricts;
	}

	public void setSubDistricts(District_SubDistricts subDistricts) {
		this.subDistricts = subDistricts;
	}

	public Caste getCaste() {
		return caste;
	}

	public void setCaste(Caste caste) {
		this.caste = caste;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getProfileUpdateDate() {
		return profileUpdateDate;
	}

	public void setProfileUpdateDate(Date profileUpdateDate) {
		this.profileUpdateDate = profileUpdateDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}


	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getProfileCreatedFor() {
		return profileCreatedFor;
	}

	public void setProfileCreatedFor(String profileCreatedFor) {
		this.profileCreatedFor = profileCreatedFor;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
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
