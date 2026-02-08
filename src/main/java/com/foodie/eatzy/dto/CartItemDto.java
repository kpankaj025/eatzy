package com.foodie.eatzy.dto;

import com.foodie.eatzy.entity.Cart;
import com.foodie.eatzy.entity.FoodItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private String cartItemId;
    private FoodItemDto foodItem;
    private int quantity;
}
