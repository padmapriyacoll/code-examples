package com.priya.bank.bankdemo.entity;

public class Customer {
	int custId;
	String custName;
	int age;
	String gender;
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", CustName=" + custName + ", age=" + age + ",gender=" + gender +"]";
	}
	

}
