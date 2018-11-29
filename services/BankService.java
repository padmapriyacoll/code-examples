package com.priya.bank.bankdemo.services;

import java.util.List;

import com.priya.bank.bankdemo.entity.Customer;

public interface BankService {
	public List<Customer> findAllCustomer();
	public Customer findByCustId(int custId);

}

