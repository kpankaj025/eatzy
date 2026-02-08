package com.foodie.eatzy.dto;

import java.time.LocalDateTime;

import com.foodie.eatzy.entity.enums.OrderStatus;
import com.foodie.eatzy.entity.enums.PaymentMode;
import com.foodie.eatzy.entity.enums.PaymentStatus;
import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderPlaceRequest {
    private String userId;
    private String restaurantId;
    private AddressDto address;
    private OrderStatus status = OrderStatus.PLACED;
    private LocalDateTime orderedAt = LocalDateTime.now();
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;
    private PaymentMode paymentMode;
    private String aboutThisOrder;

}
