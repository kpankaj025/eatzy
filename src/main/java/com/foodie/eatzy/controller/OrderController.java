package com.foodie.eatzy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.eatzy.dto.OrderDto;
import com.foodie.eatzy.dto.OrderPlaceRequest;
import com.foodie.eatzy.entity.enums.OrderStatus;
import com.foodie.eatzy.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    private ResponseEntity<OrderDto> placeOrder(@RequestBody OrderPlaceRequest orderPlaceRequest) {
        System.out.println("orderPlaceRequest: " + orderPlaceRequest);
        OrderDto orderDto = orderService.placeOrder(orderPlaceRequest);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderDto>> getOrdersByRestaurant(@PathVariable String restaurantId) {
        List<OrderDto> orders = orderService.getOrderByRestaurant(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable String userId) {
        List<OrderDto> orders = orderService.getOrderByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/delivery/{deliveryBoyId}")
    public ResponseEntity<List<OrderDto>> getOrdersByDeliveryBoy(@PathVariable String deliveryBoyId) {
        List<OrderDto> orders = orderService.getOrderByDeliveryBoy(deliveryBoyId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}/track")
    public ResponseEntity<OrderDto> trackOrder(@PathVariable String orderId) {
        OrderDto orderDto = orderService.tractOrder(orderId);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable String orderId) {
        OrderDto orderItemDto = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(orderItemDto);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody OrderStatus orderStatus) {
        OrderDto orderDto = orderService.updateOrderStataus(orderId, orderStatus);
        return ResponseEntity.ok(orderDto);
    }

}
