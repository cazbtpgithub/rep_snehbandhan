package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "OccupationCategory")
public class OccupationCategory {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "occu_id", nullable = false)
	private Long occu_id;

	@Column(name = "occu_name")
	private String occu_name;

	public Long getOccu_id() {
		return occu_id;
	}

	public void setOccu_id(Long occu_id) {
		this.occu_id = occu_id;
	}

	public String getOccu_name() {
		return occu_name;
	}

	public void setOccu_name(String occu_name) {
		this.occu_name = occu_name;
	}




}
