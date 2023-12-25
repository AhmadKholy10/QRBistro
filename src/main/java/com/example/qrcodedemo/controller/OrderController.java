package com.example.qrcodedemo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.qrcodedemo.model.Item;
import com.example.qrcodedemo.model.Order;
import com.example.qrcodedemo.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return orderService.findAll();
	}
	
	@PostMapping("/orders/save")
	public Order save(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
	

}
