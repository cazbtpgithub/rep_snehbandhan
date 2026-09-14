package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OfferImages")
public class OfferImages {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "imagename")
	private String imagename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;

	}
}
