package com.xanadutec.coreflex.model;

public class Discount {
	
	
	int id;
	Long  totalAmount;
	float discountAmount;
	String packageName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long  getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long  totalAmount) {
		this.totalAmount = totalAmount;
	}
	public float getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	

}
