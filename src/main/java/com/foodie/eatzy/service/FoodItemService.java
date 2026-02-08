package com.foodie.eatzy.service;

import java.util.List;

import com.foodie.eatzy.dto.FoodItemDto;

public interface FoodItemService {
    FoodItemDto addFood(FoodItemDto foodItemDto);

    FoodItemDto updateFood(Long foodId, FoodItemDto foodItemDto);

    void deleteFood(Long foodId);

    List<FoodItemDto> getAllFoods();

    List<FoodItemDto> getFoodsByRestaurant(String restaurantId);

    FoodItemDto getFoodById(Long foodId);
}
