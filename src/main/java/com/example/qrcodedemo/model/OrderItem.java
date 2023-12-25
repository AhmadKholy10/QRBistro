//package com.example.qrcodedemo.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//
//@Entity
//public class OrderItem {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//
//	@Column(name = "item_name", nullable = false)
//	@JsonProperty(value = "item_name")
//	private String itemName;
//
//	@Column(nullable = false)
//	private double price;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name= "order_id")
//	@JsonIgnore
//	private Order order;
//
//	public OrderItem() {
//
//	}
//	
//	public OrderItem(String itemName, double price, Order order) {
//		super();
//		this.itemName = itemName;
//		this.price = price;
//		this.order=order;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	public String getItemName() {
//		return itemName;
//	}
//
//	public void setItemName(String itemName) {
//		this.itemName = itemName;
//	}
//
//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	
//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//
//
//}
