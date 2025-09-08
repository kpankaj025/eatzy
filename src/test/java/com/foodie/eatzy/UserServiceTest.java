package com.foodie.eatzy;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodie.eatzy.entity.Restaurant;
import com.foodie.eatzy.entity.Role;
import com.foodie.eatzy.entity.User;
import com.foodie.eatzy.service.UserService;

@SpringBootTest
public class UserServiceTest {

    // @Autowired
    // private UserService userService;

    // @Test
    // public void saveUser() {

    // User user = new User();
    // user.setName("pankaj");
    // user.setEmail("kpankaj025@gmail.com");
    // user.setAvailable(true);
    // user.setAddress(" delhi");
    // user.setPassword("bjjuhh1234");
    // user.setRole(Role.ADMIN);

    // Restaurant r1 = new Restaurant();
    // r1.setId(UUID.randomUUID().toString());
    // r1.setAddress("Noida");
    // r1.setName("KFC");
    // r1.setOpen(true);

    // Restaurant r2 = new Restaurant();
    // r2.setId(UUID.randomUUID().toString());
    // r2.setAddress("Noida");
    // r2.setName("Burger King");
    // r2.setOpen(true);

    // r1.setUser(user);
    // r2.setUser(user);

    // user.getRestaurants().add(r2);
    // user.getRestaurants().add(r1);

    // userService.saveUser(user);
    // }

    // @Test
    // public void updateUser() {

    // userService.updateUser();

    // }

}
