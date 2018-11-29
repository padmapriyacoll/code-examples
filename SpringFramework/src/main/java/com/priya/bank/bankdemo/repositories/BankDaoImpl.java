package com.priya.bank.bankdemo.repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.priya.bank.bankdemo.entity.Customer;
@Repository
public class BankDaoImpl implements BankDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds)
	{
		jdbcTemplate = new JdbcTemplate(ds);
	}
	public List<Customer> findAllCustomer()
	{
		return jdbcTemplate.query("select * from bankdemo.Customer",new BeanPropertyRowMapper<Customer>(Customer.class));
	}
	public Customer findByCustId(int custId)
	{
		return jdbcTemplate.queryForObject("select * from bankdemo.Customer where custId=?", new Object[] {custId}, new BeanPropertyRowMapper<Customer>(Customer.class));
				
	}

}
