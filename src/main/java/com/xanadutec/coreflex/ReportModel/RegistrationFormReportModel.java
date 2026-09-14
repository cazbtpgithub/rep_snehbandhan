package com.xanadutec.coreflex.ReportModel;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;

public class RegistrationFormReportModel {
	
	
	String fullName;
	String profileCreatedFor;
	String country;
	String state;
	String district;
	String subDistrict;
	String city;
	String email;
	String gender;
	String aboutMe;
	String mobileNo;
	Date dateOfBirth;
	String maritalStatus;
	String physicalStatus;
	String bodyType;
	String complexion;	
	Double heightFrom;
	Double heightTo;
	Integer weight;
	String religion;
	String caste;
	String educationCategory;
	String  materialStatus;
	String eductaion;
	String occupationCategory;
	String occupation;
	String employeeIn;
	String incomeType;
	String familyPreference;
	String familyType;
	String familyValues;
	String habits;
	String eatingHabits;
	String drinkinghabits;
	String smokingHabits;
	Blob profileImage;
	String username;
	String password;
	Long incomeAmount;
	String userProfileImageName;
	//---------Partner Preferances fields-------//
	
	String partnerCountry;
	String partnerState;
	String partnerCity;
	String partnerDistrict;
	String partnerMaritalStatus;
	String partnerPhysicalStatus;
	String partnerComplexion;
	String partnerBodyType;
	String partnerReligion;
	String partnerCast;
	String partnerEmployeeIn;
	String partnerAnualIncome;
	String partnerIncomeType;
	String partnerFamilyClass;
	String partnerfamilyType;
	String partnerFamilyValues;
	String partnerEatingHabit;
	String landmark;
	
	

	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getPhysicalStatus() {
		return physicalStatus;
	}
	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getEducationCategory() {
		return educationCategory;
	}
	public void setEducationCategory(String educationCategory) {
		this.educationCategory = educationCategory;
	}
	public String getEductaion() {
		return eductaion;
	}
	public void setEductaion(String eductaion) {
		this.eductaion = eductaion;
	}
	public String getOccupationCategory() {
		return occupationCategory;
	}
	public void setOccupationCategory(String occupationCategory) {
		this.occupationCategory = occupationCategory;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getEmployeeIn() {
		return employeeIn;
	}
	public void setEmployeeIn(String employeeIn) {
		this.employeeIn = employeeIn;
	}
	
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	public String getFamilyPreference() {
		return familyPreference;
	}
	public void setFamilyPreference(String familyPreference) {
		this.familyPreference = familyPreference;
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
	public String getHabits() {
		return habits;
	}
	public void setHabits(String habits) {
		this.habits = habits;
	}
	public String getEatingHabits() {
		return eatingHabits;
	}
	public void setEatingHabits(String eatingHabits) {
		this.eatingHabits = eatingHabits;
	}
	public String getDrinkinghabits() {
		return drinkinghabits;
	}
	public void setDrinkinghabits(String drinkinghabits) {
		this.drinkinghabits = drinkinghabits;
	}
	public String getSmokingHabits() {
		return smokingHabits;
	}
	public void setSmokingHabits(String smokingHabits) {
		this.smokingHabits = smokingHabits;
	}
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getProfileCreatedFor() {
		return profileCreatedFor;
	}
	public void setProfileCreatedFor(String profileCreatedFor) {
		this.profileCreatedFor = profileCreatedFor;
	}
	public String getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}
	public Long getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(Long incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	public String getPartnerCountry() {
		return partnerCountry;
	}
	public void setPartnerCountry(String partnerCountry) {
		this.partnerCountry = partnerCountry;
	}
	public String getPartnerState() {
		return partnerState;
	}
	public void setPartnerState(String partnerState) {
		this.partnerState = partnerState;
	}
	public String getPartnerCity() {
		return partnerCity;
	}
	public void setPartnerCity(String partnerCity) {
		this.partnerCity = partnerCity;
	}
	public String getPartnerDistrict() {
		return partnerDistrict;
	}
	public void setPartnerDistrict(String partnerDistrict) {
		this.partnerDistrict = partnerDistrict;
	}
	public String getPartnerMaritalStatus() {
		return partnerMaritalStatus;
	}
	public void setPartnerMaritalStatus(String partnerMaritalStatus) {
		this.partnerMaritalStatus = partnerMaritalStatus;
	}
	public String getPartnerPhysicalStatus() {
		return partnerPhysicalStatus;
	}
	public void setPartnerPhysicalStatus(String partnerPhysicalStatus) {
		this.partnerPhysicalStatus = partnerPhysicalStatus;
	}
	public String getPartnerComplexion() {
		return partnerComplexion;
	}
	public void setPartnerComplexion(String partnerComplexion) {
		this.partnerComplexion = partnerComplexion;
	}
	public String getPartnerBodyType() {
		return partnerBodyType;
	}
	public void setPartnerBodyType(String partnerBodyType) {
		this.partnerBodyType = partnerBodyType;
	}
	public String getPartnerReligion() {
		return partnerReligion;
	}
	public void setPartnerReligion(String partnerReligion) {
		this.partnerReligion = partnerReligion;
	}
	public String getPartnerCast() {
		return partnerCast;
	}
	public void setPartnerCast(String partnerCast) {
		this.partnerCast = partnerCast;
	}
	public String getPartnerEmployeeIn() {
		return partnerEmployeeIn;
	}
	public void setPartnerEmployeeIn(String partnerEmployeeIn) {
		this.partnerEmployeeIn = partnerEmployeeIn;
	}
	public String getPartnerAnualIncome() {
		return partnerAnualIncome;
	}
	public void setPartnerAnualIncome(String partnerAnualIncome) {
		this.partnerAnualIncome = partnerAnualIncome;
	}
	public String getPartnerFamilyClass() {
		return partnerFamilyClass;
	}
	public void setPartnerFamilyClass(String partnerFamilyClass) {
		this.partnerFamilyClass = partnerFamilyClass;
	}
	public String getPartnerfamilyType() {
		return partnerfamilyType;
	}
	public void setPartnerfamilyType(String partnerfamilyType) {
		this.partnerfamilyType = partnerfamilyType;
	}
	public String getPartnerFamilyValues() {
		return partnerFamilyValues;
	}
	public void setPartnerFamilyValues(String partnerFamilyValues) {
		this.partnerFamilyValues = partnerFamilyValues;
	}
	public String getPartnerEatingHabit() {
		return partnerEatingHabit;
	}
	public void setPartnerEatingHabit(String partnerEatingHabit) {
		this.partnerEatingHabit = partnerEatingHabit;
	}
	public String getUserProfileImageName() {
		return userProfileImageName;
	}
	public void setUserProfileImageName(String userProfileImageName) {
		this.userProfileImageName = userProfileImageName;
	}
	public String getPartnerIncomeType() {
		return partnerIncomeType;
	}
	public void setPartnerIncomeType(String partnerIncomeType) {
		this.partnerIncomeType = partnerIncomeType;
	}
	
	
	
	
	
	

}
