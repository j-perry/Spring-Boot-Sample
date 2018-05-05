package com.sample.springboot.persist.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="purchase")
public class Purchase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long purchaseId;
	
	@Column(name = "customer_id")
	private long customerId;

	@Column
	private Double price;
	
	@Column
	private String item;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", insertable=false, updatable=false)
	@JsonIgnore
	private Customer customer;
	
	@Column
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public Purchase() {
		
	}

	public Purchase(long customerId, String item, Double price) {
		this.customerId = customerId;
		this.item = item;
		this.price = price;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	
}
