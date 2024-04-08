package com.beowolf23.kicksmarket.repository;

import com.beowolf23.kicksmarket.model.dao.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
