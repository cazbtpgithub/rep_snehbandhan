package com.xanadutec.coreflex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
@Entity
@Table(name ="MagazinePdf")
public class MagazinePdf
{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "magazinePdfId" , nullable = false) 
	private Integer magazinePdfId ;

	@Column(name ="magazinPdf_fileName")
	private String magazinPdf_fileName;

	@Column(name ="file_Uploaded_date")

	private Date file_Uploaded_date;
	
	@Column(name = "MagazineTitle")
	private String  magazineTitle;
	
	@Column(name = "MagazineImageNames")
	private String magazineImageNames;
	@Column(name = "magzineImgeNamesWithHash",length=300)
	private String magzineImgeNamesWithHash;

	public String getMagzineImgeNamesWithHash() {
		return magzineImgeNamesWithHash;
	}

	public void setMagzineImgeNamesWithHash(String magzineImgeNamesWithHash) {
		this.magzineImgeNamesWithHash = magzineImgeNamesWithHash;
	}

	public Integer getMagazinePdfId() {
		return magazinePdfId;
	}

	public void setMagazinePdfId(Integer magazinePdfId) {
		this.magazinePdfId = magazinePdfId;
	}

	public String getMagazinPdf_fileName() {
		return magazinPdf_fileName;
	}

	public void setMagazinPdf_fileName(String magazinPdf_fileName) {
		this.magazinPdf_fileName = magazinPdf_fileName;
	}

	public Date getFile_Uploaded_date() {
		return file_Uploaded_date;
	}

	public void setFile_Uploaded_date(Date file_Uploaded_date) {
		this.file_Uploaded_date = file_Uploaded_date;
	}

	public String getMagazineTitle() {
		return magazineTitle;
	}

	public void setMagazineTitle(String magazineTitle) {
		this.magazineTitle = magazineTitle;
	}

	public String getMagazineImageNames() {
		return magazineImageNames;
	}

	public void setMagazineImageNames(String magazineImageNames) {
		this.magazineImageNames = magazineImageNames;
	}
	

	
}
