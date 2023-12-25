package com.example.qrcodedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.qrcodedemo.model.TableDetails;



public interface TableRepository extends JpaRepository<TableDetails, Long> {
}