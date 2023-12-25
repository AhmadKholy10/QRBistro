package com.example.qrcodedemo.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "order_item",
		       joinColumns = { @JoinColumn (name = "order_id") },
		       inverseJoinColumns = { @JoinColumn (name = "item_id") })
    private List<Item> items = new ArrayList<>();
	
	@Column(name = "total_amount", nullable = false)
	//@JsonProperty(value = "total_amount")
	private double totalAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private TableDetails table;
	
	public Order() {
		this.items = new ArrayList<>();
		this.totalAmount = calculateTotalAmount();
	}
	
	public Order(List<Item> items) {
		this.items = items;
		this.totalAmount = calculateTotalAmount();
	}
	
	
    public double calculateTotalAmount() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
		this.totalAmount = calculateTotalAmount();
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public TableDetails getTable() {
		return table;
	}

	public void setTable(TableDetails table) {
		this.table = table;
	}

	@Override
	public String toString() {
		String itemsSTR = "";
		for(Item item : items) {
			itemsSTR += item.getItemName() + ", \n" +
						"Price: " + item.getPrice() + " \n";
		}
		return "Order [id=" + id + ", items=" + itemsSTR + ", totalAmount=" + totalAmount + ", table=" + table + "]";
	}
	
	
	
	

}
