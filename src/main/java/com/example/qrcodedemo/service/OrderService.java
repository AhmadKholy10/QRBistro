package com.example.qrcodedemo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.qrcodedemo.model.Item;
import com.example.qrcodedemo.model.Order;
import com.example.qrcodedemo.repository.ItemRepository;
import com.example.qrcodedemo.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private  OrderRepository orderRepository;
	
	@Autowired
    private ItemRepository itemRepository;

	

	public List<Order> findAll(){
		return orderRepository.findAll();
	}

	public Order retrieveOrder(Long id) {
		return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
	}
	
	
	public Order addOrder(Order order) {
		return orderRepository.save(new Order());
	}
	
	public Order createOrder(Order order) {
		order.getItems().forEach(item -> item.getOrders().add(order));
		return orderRepository.save(order);
	}
	
//	public Order setOrderWithItems(Set<Item> items) {
//		return orderRepository.save(new Order(items));
//	}
	
	  

	public void addItemToOrder(Long orderId, Long itemId) {
		// Fetch the order and item entities from the database
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));

			// Add the item to the order
			order.getItems().add(item);
			// Update the total amount of the order or any other relevant calculations
			order.setTotalAmount(order.calculateTotalAmount());

			// Save the updated order to the database
			orderRepository.save(order);
	  }
	
}
