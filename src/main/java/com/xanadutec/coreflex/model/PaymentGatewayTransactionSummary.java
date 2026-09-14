package com.xanadutec.coreflex.model;
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
@Table(name = "PaymentGatewayTransactionSummary")
public class PaymentGatewayTransactionSummary {
	@Id
	@GeneratedValue
	@Column(name = "transaction")
	private Integer payment_gateway_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "Kit_id")
	private Kit kit;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "package_Id")
	private Package package_Id;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "userModel")
	private UserModel userModel;

	@Column(name = "Amount")
	private String amount;

	@Column(name = "status")
	private String status;

	@Column(name = "Txn_Id")
	private String txn_Id;

	@Column(name = "productinfo")
	private String productinfo;

	@Column(name = "phone")
	private String phone;
	
	@Column(name = "Transaction_Date")
	private Date txn_date;
	

	public Integer getPayment_gateway_id() {
		return payment_gateway_id;
	}

	public void setPayment_gateway_id(Integer payment_gateway_id) {
		this.payment_gateway_id = payment_gateway_id;
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public Package getPackage_Id() {
		return package_Id;
	}

	public void setPackage_Id(Package package_Id) {
		this.package_Id = package_Id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	public String getTxn_Id() {
		return txn_Id;
	}

	public void setTxn_Id(String txn_Id) {
		this.txn_Id = txn_Id;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTxn_date() {
		return txn_date;
	}

	public void setTxn_date(Date txn_date) {
		this.txn_date = txn_date;
	}
	
	

}
