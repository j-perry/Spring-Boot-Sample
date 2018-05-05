package com.sample.springboot.persist.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="customer")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerId", insertable = false, updatable = false)
	private Long customerId;

	@Column(name = "name")
	private String name;

	@Column
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToMany(mappedBy = "customer", 
		targetEntity=Purchase.class, 
		fetch=FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<Purchase> purchases;
	
	public Customer() {
		
	}
	
	public Customer(String name) {
		this.name = name;
	}
	
	public Customer(Long customerId, String name) {
		super();
		this.customerId = customerId;
		this.name = name;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Purchase> getPurchases() {
		return purchases;
	}
	
}
