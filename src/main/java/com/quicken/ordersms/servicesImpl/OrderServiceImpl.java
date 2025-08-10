package com.quicken.ordersms.servicesImpl;

import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.enums.OrderStatus;
import com.quicken.ordersms.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createNewOrder(Product product) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public OrderStatusDTO getOrderStatus(Long orderId) {
        return null;
    }
}
