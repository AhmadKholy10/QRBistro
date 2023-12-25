package com.example.qrcodedemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.qrcodedemo.model.Order;
import com.example.qrcodedemo.model.TableDetails;
import com.example.qrcodedemo.model.student.Student;
import com.example.qrcodedemo.service.OrderService;
import com.example.qrcodedemo.service.TableService;
import com.example.qrcodedemo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/tables")
	public ResponseEntity<List<TableDetails>> getTables() throws WriterException, IOException{
		List<TableDetails> tables = tableService.findAll();
		if(tables.size() != 0) {
			for(TableDetails table : tables) {
				QRCodeGenerator.generateQRCode(table);
			}
		}
		return ResponseEntity.ok(tableService.findAll());
	}
	
	@PostMapping("/tables/save")
	public TableDetails save(@RequestBody TableDetails table) {
		return tableService.addTable(table);
	}
	
	@PostMapping("/tables/{tableId}/orders/{orderId}")
    public ResponseEntity<String> addOrderToTable(
            @PathVariable Long tableId,
            @PathVariable Long orderId) {
        try {
            // Your logic to add the item with itemId to the order with orderId
            tableService.addOrderToTable(tableId, orderId);
            return ResponseEntity.ok("Order added to the table successfully");
        } catch (EntityNotFoundException  e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding order to the table");
        }
    }
	
	@GetMapping("/tables/{tableId}/order")
	public Order getLastOrder(@PathVariable Long tableId) {
		return tableService.getLastOrder(tableId);
	}

}
