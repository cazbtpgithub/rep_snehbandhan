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
@Table(name = "Caste")
public class Caste {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "caste_id", nullable = false)
	private Long caste_id;

	@Column(name = "caste_name")
	private String caste_name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "religion_id", nullable = false)
	Religion religion_id;

	public Long getCaste_id() {
		return caste_id;
	}

	public void setCaste_id(Long caste_id) {
		this.caste_id = caste_id;
	}

	public String getCaste_name() {
		return caste_name;
	}

	public void setCaste_name(String caste_name) {
		this.caste_name = caste_name;
	}

	public Religion getReligion_id() {
		return religion_id;
	}

	public void setReligion_id(Religion religion_id) {
		this.religion_id = religion_id;
	}

}
