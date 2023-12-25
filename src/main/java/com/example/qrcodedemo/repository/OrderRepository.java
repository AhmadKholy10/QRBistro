package com.example.qrcodedemo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.qrcodedemo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Set<Order> findOrdersByItemsId(Long ItemId);
}
