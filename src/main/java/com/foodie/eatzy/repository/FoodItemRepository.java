package com.foodie.eatzy.repository;

import com.foodie.eatzy.entity.FoodItem;
import com.foodie.eatzy.entity.Restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByRestaurant(Restaurant restaurant);
}
