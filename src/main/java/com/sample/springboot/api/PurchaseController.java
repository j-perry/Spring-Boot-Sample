package com.sample.springboot.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.persist.models.Purchase;
import com.sample.springboot.persist.services.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(value = "/{customerId}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Purchase> getPurchases(@PathVariable("customerId") long customerId) throws Exception {
		return purchaseService.getPurchasesByCustomerId(customerId);
	}
	
	@RequestMapping(value = "/{customerId}/{purchaseDate}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Purchase> getPurchases(@PathVariable("customerId") long customerId, @PathVariable("purchaseDate") String purchaseDate) throws Exception {
		return purchaseService.getPurchasesByDate(customerId, purchaseDate);
	}
	
	@RequestMapping(value = "/create",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Purchase> create(@RequestBody Purchase purchase) {
		try {
			purchaseService.addPurchase(purchase);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/update",
		method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Purchase> updatePurchase(@RequestParam("customerId") Long customerId, 
			@RequestParam("purchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date purchaseDate, 
			@RequestParam("item") String item, 
			@RequestParam("price") double price) {
		try {
			purchaseService.updatePurchase(customerId, purchaseDate, item, price);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/delete",
		method = RequestMethod.GET)
	public ResponseEntity<Purchase> deletePurchase(@RequestParam("customerId") long customerId, 
			@RequestParam("purchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date purchaseDate) {
		try {
			purchaseService.deletePurchase(customerId, purchaseDate);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}