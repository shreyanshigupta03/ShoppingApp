package com.app.model;

public class Customer {
	
	private int id;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private String customerPassword;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getPassword() {
		return customerPassword;
	}
	public void setPassword(String password) {
		this.customerPassword = password;
	}
	
	public Customer(int id, String customerFirstName, String customerLastName, String customerEmail, String password) {
		super();
		this.id = id;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmail = customerEmail;
		this.customerPassword = password;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", customerEmail=" + customerEmail + ", password=" + customerPassword + "]";
	}
	
	
}
