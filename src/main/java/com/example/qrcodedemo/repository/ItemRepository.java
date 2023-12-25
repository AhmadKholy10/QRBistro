package com.example.qrcodedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.qrcodedemo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
