package com.foodie.eatzy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.eatzy.entity.Cart;
import com.foodie.eatzy.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

    List<CartItem> findByCart(Cart cart);

}
