package com.foodie.eatzy.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.foodie.eatzy.entity.enums.OrderStatus;
import com.foodie.eatzy.entity.enums.PaymentMode;
import com.foodie.eatzy.entity.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private String id;
    private UserDto user;
    private RestaurantDto restaurant;
    private AddressDto address;
    private int totalAmount;
    private OrderStatus status = OrderStatus.PLACED;
    private LocalDateTime orderedAt;
    private LocalDateTime deliveryTime;
    private UserDto deliveryBoy;
    private List<OrderItemDto> orderItems = new ArrayList<>();
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
    private String paymentId;
    private String razorpayOrderId;
}
