package com.priya.jpa.jpademo.jparepository;

import java.util.List;

import com.priya.jpa.jpademo.entity.Customer1;


public interface BankJpa {
	
		public List<Customer1> findAllCustomer();
		public Customer1 findByCustId(int custId);
		public Customer1 save(Customer1 c1);
		public void deleteByCustId(int custId);

	

	}


