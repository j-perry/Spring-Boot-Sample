package com.sample.springboot.persist.repository;

import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.springboot.persist.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Collection<Customer> findByCustomerId(Long customerId);
	
	@Transactional
	@Query(value = "SELECT c FROM Customer c JOIN c.purchases p WHERE p.customerId = :customerId")
	public Set<Customer> findCustomerPurchasesById(@Param("customerId") long customerId);
	
	@Transactional
	@Modifying
	public boolean deleteByCustomerId(long customerId);
	
}