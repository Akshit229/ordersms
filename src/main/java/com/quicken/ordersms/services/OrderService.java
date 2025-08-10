package com.quicken.ordersms.services;

import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    Order createNewOrder(Product product);
    Order getOrderById(Long orderId);
    OrderStatusDTO getOrderStatus(Long orderId);
    List<Order> getAllOrders();
}
