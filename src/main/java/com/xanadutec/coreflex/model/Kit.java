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
@Table(name = "Kit")
public class Kit {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "kit_id", nullable = false)
	private Long kit_id;

	@Column(name = "kit_username")
	private String kit_username;
	
	@Column(name = "kit_password")
	private String kit_password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "package_id", nullable = false)
	Package package_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false) 
	Status status_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="dealer")
	private Dealer dealer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="empId")
	private EmployeeRegistrationDetails empId;




	public EmployeeRegistrationDetails getEmpId() {
		return empId;
	}

	public void setEmpId(EmployeeRegistrationDetails empId) {
		this.empId = empId;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Long getKit_id() {
		return kit_id;
	}

	public void setKit_id(Long kit_id) {
		this.kit_id = kit_id;
	}

	public String getKit_username() {
		return kit_username;
	}

	public void setKit_username(String kit_username) {
		this.kit_username = kit_username;
	}

	public String getKit_password() {
		return kit_password;
	}

	public void setKit_password(String kit_password) {
		this.kit_password = kit_password;
	}

	public Package getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Package package_id) {
		this.package_id = package_id;
	}

	public Status getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Status status_id) {
		this.status_id = status_id;
	}

	

	
}
