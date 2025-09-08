package com.foodie.eatzy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.eatzy.entity.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, String> {

    Optional<Restaurant> findByName(String name);

    List<Restaurant> findByIsOpen(boolean isOpen);

}
