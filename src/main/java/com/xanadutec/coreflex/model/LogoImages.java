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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "LogoImages")
public class LogoImages {

	
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
