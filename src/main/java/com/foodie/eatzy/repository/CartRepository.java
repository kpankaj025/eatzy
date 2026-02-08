package com.foodie.eatzy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodie.eatzy.entity.Cart;
import com.foodie.eatzy.entity.User;

public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUser(User user);

    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    Optional<Cart> findByUserId(@Param("userId") String userId);

}
