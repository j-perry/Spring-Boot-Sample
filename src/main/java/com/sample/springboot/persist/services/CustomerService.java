package com.sample.springboot.persist.services;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springboot.persist.models.Customer;
import com.sample.springboot.persist.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public ArrayList<Customer> getByCustomerId(long customerId) {
		return (ArrayList<Customer>) customerRepository.findByCustomerId(customerId);
	}

	@Transactional(propagation = Propagation.REQUIRED,
		readOnly = false)
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Set<Customer> getCustomerPurchasesById(long customerId) {
		return customerRepository.findCustomerPurchasesById(customerId);
	}
	
	public boolean deleteCustomer(long customerId) {
		return customerRepository.deleteByCustomerId(customerId);
	}
	
}
