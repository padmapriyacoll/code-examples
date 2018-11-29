package com.priya.jpa.jpademo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.priya.jpa.jpademo.JpademoApplication;
import com.priya.jpa.jpademo.PersistenceConfig;
import com.priya.jpa.jpademo.entity.Customer1;
import com.priya.jpa.jpademo.jparepository.BankJpa;
import com.priya.jpa.jpademo.jpaservice.BankJpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {PersistenceConfig.class})
@ComponentScan(basePackageClasses = {com.priya.jpa.jpademo.entity.Customer1.class})
@Transactional
public class JpademoApplicationTests {
	@Autowired
	BankJpaService bj;

	@Test
	public void findByCustIdTest() {
		Customer1 c1 = bj.findByCustId(5);
		assertEquals("james",c1.getCustName());
		
	}
	@Test
	@Transactional
	public void deleteByIdTest() {
		bj.deleteByCustId(9);
		assertNull(bj.findByCustId(9));
	}
	
	/*@Test
	public void saveTest() {
		Customer1 c1 = bj.findByCustId(7);
		assertEquals("mark",c1.getCustName());
		c1.setCustName("mark");
		bj.save(c1);
		Customer1 c2=bj.findByCustId(7);
		assertEquals("mark",c2.getCustName());
		
	}*/

}
