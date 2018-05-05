package com.sample.springboot.api;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.persist.models.Customer;
import com.sample.springboot.persist.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value="/")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping(value = "/{customerId}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Customer> getRecordsByCustomerId(@PathVariable("customerId") long customerId) throws Exception {
		return customerService.getByCustomerId(customerId);
	}
	
	@RequestMapping(value = "/purchases",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<Customer> getPurchasesByCustomerId(@RequestParam("customerId") long customerId) {
		return customerService.getCustomerPurchasesById(customerId);
	}
	
	@RequestMapping(value = "/create",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer addCustomer(@RequestBody Customer customer) throws Exception {
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping(value = "/delete",
		method = RequestMethod.DELETE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") long customerId) throws Exception {
		try {
			this.customerService.deleteCustomer(customerId);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}
