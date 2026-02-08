package com.foodie.eatzy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequest {

    private Long productId;
    private String userId;
    private int quantity;

}
