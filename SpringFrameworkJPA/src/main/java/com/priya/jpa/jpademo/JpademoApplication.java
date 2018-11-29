package com.priya.jpa.jpademo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.priya.jpa.jpademo.entity.Customer1;
import com.priya.jpa.jpademo.jpaservice.BankJpaService;
import com.priya.jpa.jpademo.jpaservice.BankJpaServiceImpl;


public class JpademoApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
		BankJpaService bs1=context.getBean(BankJpaServiceImpl.class);
		for(Customer1 c:bs1.findAllCustomer())
		{
			System.out.println(c);
		}
		
		System.out.println(bs1.findByCustId(1));
		
	     Customer1 c2= new Customer1(35,"james","male");
		
		System.out.println(bs1.save(c2));
	
		bs1.deleteByCustId(4);
	}


}

