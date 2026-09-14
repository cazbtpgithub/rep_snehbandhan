package com.xanadutec.coreflex.DealerReport;

public class DealerReportModel {
	
	int dealerId;
	String fullName ;
	String email;
	String contactNo;
	String username;
	String password;
	String branch;
	String dealerCode;
	double discount;
	String address;
	String officeNo;
	String pincode;
	String packageName;
	String kituserName;
	double price;
	double discountAmount;
	int totalKit;

	int usedKit;
	int returnKit;
	int remainingKit;
	java.util.Date revenueDate;
	String mainDealer;
	java.util.Date regDate;

	
	public java.util.Date getRegDate() {
		return regDate;
	}
	public void setRegDate(java.util.Date regDate) {
		this.regDate = regDate;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getTotalKit() {
		return totalKit;
	}
	public void setTotalKit(int totalKit) {
		this.totalKit = totalKit;
	}
	public int getUsedKit() {
		return usedKit;
	}
	public void setUsedKit(int usedKit) {
		this.usedKit = usedKit;
	}
	public int getReturnKit() {
		return returnKit;
	}
	public void setReturnKit(int returnKit) {
		this.returnKit = returnKit;
	}
	public int getRemainingKit() {
		return remainingKit;
	}
	public void setRemainingKit(int remainingKit) {
		this.remainingKit = remainingKit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getKituserName() {
		return kituserName;
	}
	public void setKituserName(String kituserName) {
		this.kituserName = kituserName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public java.util.Date getRevenueDate() {
		return revenueDate;
	}
	public void setRevenueDate(java.util.Date revenueDate) {
		this.revenueDate = revenueDate;
	}
	public String getMainDealer() {
		return mainDealer;
	}
	public void setMainDealer(String mainDealer) {
		this.mainDealer = mainDealer;
	}
}
