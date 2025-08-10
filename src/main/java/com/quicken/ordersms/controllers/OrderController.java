package com.quicken.ordersms.controllers;

import com.quicken.ordersms.dtos.OrderDTO;
import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/status/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatus(@PathVariable Long id) {
        OrderStatusDTO orderStatusDTO = orderService.getOrderStatus(id);
        return ResponseEntity.ok(orderStatusDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.createNewOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
