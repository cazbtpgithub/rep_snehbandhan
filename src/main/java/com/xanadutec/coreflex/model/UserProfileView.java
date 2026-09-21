package com.xanadutec.coreflex.model;

import java.util.Date;
import java.util.List;

public class UserProfileView {

	private String name;
	private String location;
	private int age;
	private String education;
	private String occupation;
	private String caste;
	private String aboutHer;
	private Date viewedOnDate;
	private List<String> imageObject;
	private Long visitorUserId;
	private Long userId;
	private String onelineStatus;
	private String landmark;
	
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	
	public String getOnelineStatus() {
		return onelineStatus;
	}
	public void setOnelineStatus(String onelineStatus) {
		this.onelineStatus = onelineStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getAboutHer() {
		return aboutHer;
	}
	public void setAboutHer(String aboutHer) {
		this.aboutHer = aboutHer;
	}
	public Date getViewedOnDate() {
		return viewedOnDate;
	}
	public void setViewedOnDate(Date viewedOnDate) {
		this.viewedOnDate = viewedOnDate;
	}
	
	public List<String> getImageObject() {
		return imageObject;
	}
	public void setImageObject(List<String> imageObject) {
		this.imageObject = imageObject;
	}
	public Long getVisitorUserId() {
		return visitorUserId;
	}
	public void setVisitorUserId(Long visitorUserId) {
		this.visitorUserId = visitorUserId;
	}

	private String gender;
	private String height;
	private Long viewCount;

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}

	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

}
