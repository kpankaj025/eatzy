package com.foodie.eatzy.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryEarningDto {

    private Long id;

    private OrderDto order;
    private int amount;
    private LocalDateTime deliveredTime;

}
