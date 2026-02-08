package com.foodie.eatzy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodie.eatzy.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    List<Restaurant> findByIsActive(boolean active);

    List<Restaurant> findByOpen(boolean open);

    List<Restaurant> findByIsActiveAndOpen(boolean isActive, boolean open);

    @Query("SELECT r FROM Restaurant r WHERE r.owner.id = :ownerId")
    List<Restaurant> findByOwnerId(@Param("ownerId") String ownerId);

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :nameKeyword, '%'))")
    List<Restaurant> searchByName(@Param("nameKeyword") String nameKeyword);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN r.address a WHERE " +
            "LOWER(a.addressLine) LIKE LOWER(CONCAT('%', :address, '%')) OR " +
            "LOWER(a.city) LIKE LOWER(CONCAT('%', :address, '%')) OR " +
            "LOWER(a.state) LIKE LOWER(CONCAT('%', :address, '%'))")
    List<Restaurant> searchByAddress(@Param("address") String address);
}
