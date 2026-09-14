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
@Table(name = "State_Districts")
public class State_Districts {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "state_district_id", nullable = false)
	private Integer state_district_id;

	@Column(name = "district_Name")
	private String district_Name;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="state_Country_id",nullable=false)
	State_Country state_Country;


	public Integer getState_district_id() {
		return state_district_id;
	}


	public void setState_district_id(Integer state_district_id) {
		this.state_district_id = state_district_id;
	}


	public String getDistrict_Name() {
		return district_Name;
	}


	public void setDistrict_Name(String district_Name) {
		this.district_Name = district_Name;
	}


	public State_Country getState_Country() {
		return state_Country;
	}


	public void setState_Country(State_Country state_Country) {
		this.state_Country = state_Country;
	}
	
	
}
