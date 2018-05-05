package com.sample.springboot.persist.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.springboot.persist.models.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	@Transactional
	@Modifying
	Long deleteByCustomerIdAndDate(Long customerId, Date date);
	
	@Transactional
	@Modifying
	@Query("UPDATE Purchase p SET p.item = :item, p.price = :price WHERE p.customerId = :customerId AND p.date = :date")
	int updatePurchase(@Param("customerId") Long customerId, @Param("date") Date date, @Param("item") String item, @Param("price") double price);
	
	@Transactional
	@Modifying
	List<Purchase> getPurchasesByCustomerId(long customerId);
	
	@Transactional
	List<Purchase> getPurchasesByCustomerIdAndDate(long customerId, Date date);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT p FROM Customer c JOIN c.purchases p WHERE c.id = :customerId")
	List<Purchase> getCustomerPurchases(@Param("customerId") long customerId);
	
}
