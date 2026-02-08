package com.foodie.eatzy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.eatzy.entity.Address;
import com.foodie.eatzy.entity.User;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);

}
