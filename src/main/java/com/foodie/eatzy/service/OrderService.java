package com.foodie.eatzy.service;

import java.util.List;

import com.foodie.eatzy.dto.OrderDto;
import com.foodie.eatzy.dto.OrderPlaceRequest;
import com.foodie.eatzy.entity.enums.OrderStatus;

public interface OrderService {

    OrderDto placeOrder(OrderPlaceRequest orderPlaceRequest);

    List<OrderDto> getOrders();

    List<OrderDto> getOrderByRestaurant(String restaurantId);

    List<OrderDto> getOrderByUser(String userId);

    List<OrderDto> getOrderByDeliveryBoy(String deliveryBoyId);

    OrderDto tractOrder(String orderId);

    OrderDto cancelOrder(String orderId);

    OrderDto updateOrderStataus(String orderId, OrderStatus orderStatus);

}
