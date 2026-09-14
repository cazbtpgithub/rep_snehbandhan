package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "EducationCategory")
public class EducationCategory {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "edu_cat_id", nullable = false)
	private Long edu_cat_id;

	@Column(name = "edu_cat_name")
	private String edu_cat_name;

	public Long getEdu_cat_id() {
		return edu_cat_id;
	}

	public void setEdu_cat_id(Long edu_cat_id) {
		this.edu_cat_id = edu_cat_id;
	}

	public String getEdu_cat_name() {
		return edu_cat_name;
	}

	public void setEdu_cat_name(String edu_cat_name) {
		this.edu_cat_name = edu_cat_name;
	}


}
