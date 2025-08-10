package com.quicken.ordersms.dtos;

import com.quicken.ordersms.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderStatusDTO {
    private Long orderId;
    private OrderStatus orderStatus;
}
