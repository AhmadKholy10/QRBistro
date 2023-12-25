package com.example.qrcodedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.qrcodedemo.model.Item;
import com.example.qrcodedemo.service.ItemService;
import com.example.qrcodedemo.service.OrderService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/items")
	public List<Item> getAllItems(){
		return itemService.findAll();
	}
	
	@PostMapping("/items")
	public Item save(@RequestBody Item item) {
		return itemService.addItem(item);
	}
	
	@PostMapping("/orders/{orderId}/items/{itemId}")
    public ResponseEntity<String> addItemToOrder(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        try {
            // Your logic to add the item with itemId to the order with orderId
            orderService.addItemToOrder(orderId, itemId);
            return ResponseEntity.ok("Item added to the order successfully");
        } catch (EntityNotFoundException  e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding item to the order");
        }
    }

}
