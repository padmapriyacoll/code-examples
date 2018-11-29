package com.priya.jpa.jpademo.jparepository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.priya.jpa.jpademo.entity.Customer1;
	
@Repository
@Transactional
public class BankJpaImpl implements BankJpa{
		
	@PersistenceContext
	EntityManager em;
	
	public List<Customer1> findAllCustomer()
	{
			TypedQuery<Customer1> namedQuery=em.createNamedQuery("find_all_customers",Customer1.class);
			return namedQuery.getResultList();
	}
	
	public Customer1 findByCustId(int custId)
	{
			return em.find(Customer1.class,custId);
					
	}
	@Transactional
	public Customer1 save(Customer1 c1)
	{
		if(c1.getCustId()== 0)
		{
			em.persist(c1);
		}
		else
		{
			em.merge(c1);
		}
		
		return c1;
			
	}
	@Transactional
	public void deleteByCustId(int custId)
	{
		Customer1 c1 = findByCustId(custId);
		em.remove(c1);
	}

}


