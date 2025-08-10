package com.quicken.ordersms.mapper;

import com.quicken.ordersms.dtos.OrderDTO;
import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "orderStatus", target = "status")
    OrderDTO toOrderDTO(Order order);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "orderStatus", target = "orderStatus")
    OrderStatusDTO toOrderStatusDTO(Order order);
}