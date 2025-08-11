package com.quicken.ordersms.controllers;

import com.quicken.ordersms.dtos.OrderDTO;
import com.quicken.ordersms.dtos.OrderStatusDTO;
import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get the status of an order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderStatusDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @GetMapping("/status/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatus(@PathVariable Long id) {
        OrderStatusDTO orderStatusDTO = orderService.getOrderStatus(id);
        return ResponseEntity.ok(orderStatusDTO);
    }

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Order creation started",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid order data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.createNewOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(order);
    }

    @Operation(summary = "Get all orders")
    @ApiResponse(responseCode = "200", description = "List of orders",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class)))
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Get an order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
