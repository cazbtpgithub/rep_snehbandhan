package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Religion")
public class Religion {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "religion_id", nullable = false)
	private Long religion_id;

	@Column(name = "religion_name")
	private String religion_name;

	public Long getReligion_id() {
		return religion_id;
	}

	public void setReligion_id(Long religion_id) {
		this.religion_id = religion_id;
	}

	public String getReligion_name() {
		return religion_name;
	}

	public void setReligion_name(String religion_name) {
		this.religion_name = religion_name;
	}


}
