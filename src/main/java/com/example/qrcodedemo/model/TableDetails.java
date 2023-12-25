package com.example.qrcodedemo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table; 

@Entity
@Table(name = "tables")
public class TableDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@OneToMany(mappedBy = "table")
	private List<Order> orders = new ArrayList<>();
	
	@Column(name = "total_price")
	private double totalPrice;
	 
	public TableDetails() {
	    this.totalPrice = calculateTotalPrice();
	}
	
	
	
	public double calculateTotalPrice() {
       return orders.stream()
                .mapToDouble(Order::calculateTotalAmount)
                .sum();
    }



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public List<Order> getOrders() {
		return orders;
	}



	public void setOrders(List<Order> orders) {
		this.orders = orders;
		this.totalPrice = calculateTotalPrice();
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	



}
