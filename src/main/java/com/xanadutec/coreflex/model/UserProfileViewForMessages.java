package com.xanadutec.coreflex.model;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserProfileViewForMessages {


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
	private String message;
	private String communication_Type;
	private Date communication_date;
	private Date status_date;

	Long  sortlistedProfileUserWithDate;
	
	
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCommunication_Type() {
		return communication_Type;
	}
	
	public void setCommunication_Type(String communication_Type) {
		this.communication_Type = communication_Type;
	}
	public Date getCommunication_date() {
		return communication_date;
	}
	public void setCommunication_date(Date communication_date) {
		this.communication_date = communication_date;
	}
	public Date getStatus_date() {
		return status_date;
	}
	public void setStatus_date(Date status_date) {
		this.status_date = status_date;
	}
	public Long getSortlistedProfileUserWithDate() {
		return sortlistedProfileUserWithDate;
	}
	public void setSortlistedProfileUserWithDate(Long sortlistedProfileUserWithDate) {
		this.sortlistedProfileUserWithDate = sortlistedProfileUserWithDate;
	}


}
