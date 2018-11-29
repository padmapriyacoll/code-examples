package com.priya.bank.bankdemo.repositories;

import java.util.List;

import com.priya.bank.bankdemo.entity.Customer;

public interface BankDao {
	
		public List<Customer> findAllCustomer();
		public Customer findByCustId(int custId);

	}


