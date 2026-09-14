package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EmailTime")
public class EmailTime {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emaiId", nullable = false)
	private Integer emaiId;
	@Column(name = "emailDate")
	private Date emailDate;
	public Integer getEmaiId() {
		return emaiId;
	}
	public void setEmaiId(Integer emaiId) {
		this.emaiId = emaiId;
	}
	public Date getEmailDate() {
		return emailDate;
	}
	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}

}
