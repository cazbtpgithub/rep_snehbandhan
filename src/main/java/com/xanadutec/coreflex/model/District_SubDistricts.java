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
@Table(name = "District_SubDistricts")
public class District_SubDistricts {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "district_subDistricts_id", nullable = false)
	private Integer district_subDistricts_id;

	@Column(name = "subdistricts_Name")
	private String subdistricts_Name;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="state_district_id",nullable=false)
	State_Districts state_Districts;

	public Integer getDistrict_subDistricts_id() {
		return district_subDistricts_id;
	}

	public void setDistrict_subDistricts_id(Integer district_subDistricts_id) {
		this.district_subDistricts_id = district_subDistricts_id;
	}

	public String getSubdistricts_Name() {
		return subdistricts_Name;
	}

	public void setSubdistricts_Name(String subdistricts_Name) {
		this.subdistricts_Name = subdistricts_Name;
	}

	public State_Districts getState_Districts() {
		return state_Districts;
	}

	public void setState_Districts(State_Districts state_Districts) {
		this.state_Districts = state_Districts;
	}

}
