package com.example.Supermarket.repository;

import com.example.Supermarket.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Customer, Long> {

}
