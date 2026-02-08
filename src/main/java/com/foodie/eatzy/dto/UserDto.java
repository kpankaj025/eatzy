package com.foodie.eatzy.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.foodie.eatzy.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    private String name;

    private String email;

    private String password;

    // private String phoneNumber;

    private Role role = Role.ROLE_USER; // ADMIN, USER, DELIVERY_BOY, RESTAURANT

    private boolean isAvailable = true; // applicable for delivery boy

    // feel free to add more fields ad required

    private LocalDate createdDate;
    private boolean enabled = true;
    // private List<RestaurantDto> restaurants = new ArrayList<>();
    private List<AddressDto> addresses = new ArrayList<>();

}
