package com.example.qrcodedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.qrcodedemo.model.Item;
import com.example.qrcodedemo.model.Order;
import com.example.qrcodedemo.model.TableDetails;
import com.example.qrcodedemo.repository.OrderRepository;
import com.example.qrcodedemo.repository.TableRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TableService {
	
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<TableDetails> findAll(){
		return tableRepository.findAll();
	}
	
	public TableDetails retrieveTable(Long id) {
		return tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table not found with id " + id));
	}
	
	public TableDetails addTable(TableDetails order) {
		return tableRepository.save(new TableDetails());
	}
	
	public void addOrderToTable(Long tableId, Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

		TableDetails table = tableRepository.findById(tableId)
				.orElseThrow(() -> new EntityNotFoundException("Table not found with id: " + tableId));
            
		    order.setTable(table);
		    orderRepository.save(order);
			table.getOrders().add(order);
			table.setTotalPrice(table.calculateTotalPrice());
			tableRepository.save(table);
	  }
	
	public Order getLastOrder(Long tableId) {
		TableDetails table = tableRepository.findById(tableId)
				.orElseThrow(() -> new EntityNotFoundException("Table not found with id: " + tableId));
		Order order = table.getOrders().get(table.getOrders().size() - 1);
		return order;
	}

}
