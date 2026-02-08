package com.foodie.eatzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.eatzy.dto.AddItemToCartRequest;
import com.foodie.eatzy.dto.CartDto;
import com.foodie.eatzy.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDto> addItemToCart(@Valid @RequestBody AddItemToCartRequest request) {
        CartDto cart = cartService.addItemToCart(request);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        CartDto cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/user/{userId}/item/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDto> removeItemFromCart(
            @PathVariable String userId,
            @PathVariable String cartItemId) {
        CartDto cart = cartService.removeItemFromCart(cartItemId, userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("card is cleared");
    }

}
