package com.example.qrcodedemo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.qrcodedemo.model.Item;
import com.example.qrcodedemo.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

	@Autowired
	private  ItemRepository itemRepository;

	

	public List<Item> findAll(){
		return itemRepository.findAll();
	}

	public Item retrieveItem(Long id) {
		return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id " + id));
	}
	
	
	public Item addItem (Item item) {
		return itemRepository.save(item);
	}

}
