package com.priya.bank.bankdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.priya.bank.bankdemo.entity.Customer;
import com.priya.bank.bankdemo.repositories.BankDao;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	 BankDao bd;

	public List<Customer> findAllCustomer()
	{
		return bd.findAllCustomer();
	}
	public Customer findByCustId(int custId)
	{
		return bd.findByCustId(custId);
				
	}
}
