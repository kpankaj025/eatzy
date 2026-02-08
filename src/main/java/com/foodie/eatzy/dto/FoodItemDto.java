package com.foodie.eatzy.dto;

import com.foodie.eatzy.entity.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    private FoodType foodType = FoodType.VEG;
    private String imageUrl;
    private int discountAmount;
    private String restaurantId;

}