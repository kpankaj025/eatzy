package com.foodie.eatzy.dto;

public record PaymentVerifyDto(
        String razorpayOrderId,
        String razorpayPaymentId,
        String razorpaySignature) {
}
