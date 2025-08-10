package com.quicken.ordersms.repositories;

import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
