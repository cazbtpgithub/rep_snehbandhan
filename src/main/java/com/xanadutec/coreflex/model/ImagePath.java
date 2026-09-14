package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ImagePath")
public class ImagePath {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "path")
	private long path_id;

	@Column(name = "path_name")
	private String path_name;

	public long getPath_id() {
		return path_id;
	}

	public void setPath_id(long path_id) {
		this.path_id = path_id;
	}

	public String getPath_name() {
		return path_name;
	}

	public void setPath_name(String path_name) {
		this.path_name = path_name;
	}

	
	

}
