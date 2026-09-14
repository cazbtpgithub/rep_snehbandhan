package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PrivacyPolicy")
public class PrivacyPolicy {
	
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "privacyText",length=4000)
	String privacyText;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrivacyText() {
		return privacyText;
	}

	public void setPrivacyText(String privacyText) {
		this.privacyText = privacyText;
	}
	
	

}
