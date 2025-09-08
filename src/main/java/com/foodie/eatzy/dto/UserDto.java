package com.foodie.eatzy.dto;

import com.foodie.eatzy.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;

    private boolean isAvailable = true;

    private Role role;

}
