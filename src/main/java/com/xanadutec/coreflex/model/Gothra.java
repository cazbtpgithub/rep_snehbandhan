package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Gothra")
public class Gothra {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gothra_id", nullable = false)
	private Integer gothra_id;

	@Column(name = "gothra_Name")
	private String gothra_Name;

	public Integer getGothra_id() {
		return gothra_id;
	}

	public void setGothra_id(Integer gothra_id) {
		this.gothra_id = gothra_id;
	}

	public String getGothra_Name() {
		return gothra_Name;
	}

	public void setGothra_Name(String gothra_Name) {
		this.gothra_Name = gothra_Name;
	}


	

}
