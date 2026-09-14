package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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
@Table(name="DealerSellKitDetails")
public class DealerSellKitDetails {
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "packageId", nullable = false)
	Package packageId;
	
	@Column(name="updateDate")
	Date updateDate;
	
	@Column(name="discountAmount")
	float discountAmount;
	
	long priceAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dealer", nullable = false)
	private Dealer dealer;

	
	
	

	public Package getPackageId() {
		return packageId;
	}

	public void setPackageId(Package packageId) {
		this.packageId = packageId;
	}

	public long getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(long priceAmount) {
		this.priceAmount = priceAmount;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}
	

	

}
