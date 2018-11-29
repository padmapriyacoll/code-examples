package com.priya.bank.bankdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.priya.bank.bankdemo.entity.Customer;
import com.priya.bank.bankdemo.services.BankServiceImpl;

public class BankdemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		BankServiceImpl bs1=context.getBean(BankServiceImpl.class);
		for(Customer c:bs1.findAllCustomer())
		{
			System.out.println(c);
		}
	}
}
