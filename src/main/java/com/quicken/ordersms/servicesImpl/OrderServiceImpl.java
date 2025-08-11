package com.quicken.ordersms.servicesImpl;

import com.quicken.ordersms.dtos.OrderDTO;
import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.enums.OrderStatus;
import com.quicken.ordersms.exceptions.BadRequestException;
import com.quicken.ordersms.mapper.OrderMapper;
import com.quicken.ordersms.repositories.OrderRepository;
import com.quicken.ordersms.repositories.ProductRepository;
import com.quicken.ordersms.services.OrderProcessingServiceAsync;
import com.quicken.ordersms.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProcessingServiceAsync orderProcessingServiceAsync;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO createNewOrder(OrderDTO orderDTO) {
        Product existingProduct = productRepository.findById(orderDTO.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Order order = new Order();
        order.setProduct(existingProduct);
        order.setOrderStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        orderProcessingServiceAsync.processOrderAsync(savedOrder.getId());
        return orderMapper.toOrderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        return orderMapper.toOrderDTO(order);
    }

    @Override
    public OrderStatusDTO getOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        return orderMapper.toOrderStatusDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderDTO).collect(Collectors.toList());
    }
}
