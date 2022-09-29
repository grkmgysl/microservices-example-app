package com.grkmgysl.orderservice.repository;

import com.grkmgysl.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
