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
@Table(name = "Education")
public class Education {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "education_id", nullable = false)
	private Long education_id;

	@Column(name = "education_name")
	private String education_name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "edu_cat_id", nullable = false)
	EducationCategory edu_cat_id;

	public Long getEducation_id() {
		return education_id;
	}

	public void setEducation_id(Long education_id) {
		this.education_id = education_id;
	}

	public String getEducation_name() {
		return education_name;
	}

	public void setEducation_name(String education_name) {
		this.education_name = education_name;
	}

	public EducationCategory getEdu_cat_id() {
		return edu_cat_id;
	}

	public void setEdu_cat_id(EducationCategory edu_cat_id) {
		this.edu_cat_id = edu_cat_id;
	}

	
}
