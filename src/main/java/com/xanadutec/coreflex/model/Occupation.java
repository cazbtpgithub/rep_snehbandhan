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
@Table(name = "Occupation")
public class Occupation {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "occupation_id", nullable = false)
	private Long occupation_id;

	@Column(name = "occupation_name")
	private String occupation_name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "occ_cat_id", nullable = false)
	OccupationCategory occ_cat_id;

	public Long getOccupation_id() {
		return occupation_id;
	}

	public void setOccupation_id(Long occupation_id) {
		this.occupation_id = occupation_id;
	}

	public String getOccupation_name() {
		return occupation_name;
	}

	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}

	public OccupationCategory getOcc_cat_id() {
		return occ_cat_id;
	}

	public void setOcc_cat_id(OccupationCategory occ_cat_id) {
		this.occ_cat_id = occ_cat_id;
	}

	

	
}
