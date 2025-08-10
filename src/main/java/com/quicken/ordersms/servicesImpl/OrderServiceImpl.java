package com.quicken.ordersms.servicesImpl;

import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.enums.OrderStatus;
import com.quicken.ordersms.repositories.OrderRepository;
import com.quicken.ordersms.repositories.ProductRepository;
import com.quicken.ordersms.services.OrderProcessingServiceAsync;
import com.quicken.ordersms.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProcessingServiceAsync orderProcessingServiceAsync;
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderProcessingServiceAsync orderProcessingServiceAsync){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProcessingServiceAsync = orderProcessingServiceAsync;
    }

    @Override
    public Order createNewOrder(Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setProduct(existingProduct);
        order.setOrderStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        orderProcessingServiceAsync.processOrderAsync(savedOrder.getId());
        return savedOrder;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @Override
    public OrderStatusDTO getOrderStatus(Long orderId) {
        Order order = this.getOrderById(orderId);
        return new OrderStatusDTO(orderId, order.getOrderStatus());
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
