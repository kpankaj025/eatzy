package com.foodie.eatzy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.eatzy.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByNameContaining(String keyword);

    Page<User> findAll(Pageable pageable);

}
