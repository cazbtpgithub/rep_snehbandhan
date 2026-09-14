package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "MotherTongue")
public class MotherTongue {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mother_tongue_id", nullable = false)
	private Long mother_tongue_id;

	@Column(name = "mother_tongue_name")
	private String mother_tongue_name;

	public Long getMother_tongue_id() {
		return mother_tongue_id;
	}

	public void setMother_tongue_id(Long mother_tongue_id) {
		this.mother_tongue_id = mother_tongue_id;
	}

	public String getMother_tongue_name() {
		return mother_tongue_name;
	}

	public void setMother_tongue_name(String mother_tongue_name) {
		this.mother_tongue_name = mother_tongue_name;
	}
	
	
}
