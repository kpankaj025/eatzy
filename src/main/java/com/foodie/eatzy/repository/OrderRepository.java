package com.foodie.eatzy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodie.eatzy.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.restaurant.id = ?1")
    List<Order> findByRestaurantId(String restaurantId);

    @Query("SELECT o FROM Order o WHERE o.user.id = ?1")
    List<Order> findByUserId(String userId);

    @Query("SELECT o FROM Order o WHERE o.deliveryBoy.id = ?1")
    List<Order> findByDeliveryBoyId(String deliveryBoyId);
}
