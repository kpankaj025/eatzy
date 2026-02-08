package com.foodie.eatzy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.eatzy.entity.DeliveryEarning;
import com.foodie.eatzy.entity.User;

public interface DeliveryEarningRepository extends JpaRepository<DeliveryEarning, Long> {

    List<DeliveryEarning> findByDeliveryBoy(User deliveryBoy);

}
