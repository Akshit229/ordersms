package com.quicken.ordersms.servicesImpl;

import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.enums.OrderStatus;
import com.quicken.ordersms.repositories.OrderRepository;
import com.quicken.ordersms.services.OrderProcessingServiceAsync;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingServiceAsyncImpl implements OrderProcessingServiceAsync {
    private final OrderRepository orderRepository;
    public OrderProcessingServiceAsyncImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Value("${spring.application.delay-ms:3000}")
    private long delayMs;

    @Async
    public void processOrderAsync(Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            order.setOrderStatus(OrderStatus.PROCESSING);
            orderRepository.save(order);

            // Simulate processing delay 3 seconds
            Thread.sleep(delayMs);

            // Randomly decide completed or failed
            order.setOrderStatus(Math.random() > 0.5 ? OrderStatus.COMPLETED : OrderStatus.FAILED);
            orderRepository.save(order);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
