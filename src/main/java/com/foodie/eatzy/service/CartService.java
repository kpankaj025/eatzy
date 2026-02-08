package com.foodie.eatzy.service;

import java.util.List;

import com.foodie.eatzy.dto.AddItemToCartRequest;
import com.foodie.eatzy.dto.CartDto;
import com.foodie.eatzy.entity.CartItem;

public interface CartService {

    CartDto addItemToCart(AddItemToCartRequest addItemToCartRequest);

    CartDto getCart(String userId);

    CartDto removeItemFromCart(String cartItemId, String userId);

    List<CartItem> getCartItems(String userId);

    void clearCart(String userId);
}
