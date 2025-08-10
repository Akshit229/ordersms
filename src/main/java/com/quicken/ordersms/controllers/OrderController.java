package com.quicken.ordersms.controllers;

import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatus(@PathVariable Long id) {
        OrderStatusDTO orderStatusDTO = orderService.getOrderStatus(id);
        return ResponseEntity.ok(orderStatusDTO);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Product product) {
        Order order = orderService.createNewOrder(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderById(@PathVariable Long id) {
        OrderStatusDTO orderStatusDTO = orderService.getOrderStatus(id);
        return ResponseEntity.ok(orderStatusDTO);
    }
}
