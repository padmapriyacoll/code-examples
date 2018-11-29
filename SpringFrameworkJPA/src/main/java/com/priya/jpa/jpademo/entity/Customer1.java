package com.priya.jpa.jpademo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="find_all_customers",query="select c from Customer1 c")
public class Customer1 {
	    @Id
	    @GeneratedValue
		int custId;
		String custName;
		int age;
		String gender;
		public Customer1() {
			
		}
		public Customer1(int age,String custName,String gender)
		{
			this.age=age;
			this.custName=custName;
			this.gender=gender;
		}
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
			return "Customer1 [custId=" + custId + ", CustName=" + custName + ", age=" + age + ",gender=" + gender +"]";
		}
		

}
