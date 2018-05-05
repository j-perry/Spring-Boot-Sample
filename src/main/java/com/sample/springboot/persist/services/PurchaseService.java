package com.sample.springboot.persist.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.springboot.persist.models.Purchase;
import com.sample.springboot.persist.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepo;

	public List<Purchase> getPurchasesByCustomerId(long customerId) {
		return purchaseRepo.getPurchasesByCustomerId(customerId);
	}
	
	public List<Purchase> getPurchasesByDate(long customerId, String purchaseDate) throws Exception {
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDate);
		return purchaseRepo.getPurchasesByCustomerIdAndDate(customerId, d);
	}
	
	public Purchase addPurchase(Purchase purchase) {
		return purchaseRepo.save(purchase);
	}
	
	public int updatePurchase(Long customerId, Date date, String item, double price) {
		return purchaseRepo.updatePurchase(customerId, date, item, price);
	}
	
	public Long deletePurchase(Long customerId, Date date) {
		return purchaseRepo.deleteByCustomerIdAndDate(customerId, date);
	}
	
}
