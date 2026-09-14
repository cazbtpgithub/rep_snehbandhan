package com.xanadutec.coreflex.model;


import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "State_Country")
public class State_Country {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "state_Country_id", nullable = false)
	private Integer state_Country_id;

	@Column(name = "state_Name")
	private String state_Name;

		@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id",nullable=false)
	Country country;

		
	public Integer getState_Country_id() {
		return state_Country_id;
	}


	public void setState_Country_id(Integer state_Country_id) {
		this.state_Country_id = state_Country_id;
	}


	public String getState_Name() {
		return state_Name;
	}


	public void setState_Name(String state_Name) {
		this.state_Name = state_Name;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}
}
