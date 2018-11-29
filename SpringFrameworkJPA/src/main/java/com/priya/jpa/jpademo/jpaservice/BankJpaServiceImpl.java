package com.priya.jpa.jpademo.jpaservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.priya.jpa.jpademo.entity.Customer1;
import com.priya.jpa.jpademo.jparepository.BankJpa;

@Service
public class BankJpaServiceImpl implements BankJpaService {
 @Autowired
 BankJpa bj;

	public List<Customer1> findAllCustomer()
	{
		return bj.findAllCustomer();
	}
	public Customer1 findByCustId(int custId)
	{
				return bj.findByCustId(custId);
						
	}

	public Customer1 save(Customer1 c1)
	{
		return bj.save(c1);
	}

	public void deleteByCustId(int custId)
	{
		bj.deleteByCustId(custId);
	}

}

