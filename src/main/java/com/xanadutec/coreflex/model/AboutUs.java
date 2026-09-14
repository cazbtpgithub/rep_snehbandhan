package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AboutUs")
public class AboutUs {

	
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "firstPortion",length=2000)
	String firstPortion;

	@Column(name = "secondPortion",length=2000)
	String secondPortion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstPortion() {
		return firstPortion;
	}

	public void setFirstPortion(String firstPortion) {
		this.firstPortion = firstPortion;
	}

	public String getSecondPortion() {
		return secondPortion;
	}

	public void setSecondPortion(String secondPortion) {
		this.secondPortion = secondPortion;
	}

}
