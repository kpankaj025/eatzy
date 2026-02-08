package com.foodie.eatzy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foodie.eatzy.dto.UserDto;

public interface UserService {

    UserDto saveUser(UserDto user);

    UserDto updateUser(UserDto user, String userId);

    List<UserDto> getAll();

    List<UserDto> getUserByName(String name);

    UserDto getById(String id);

    Page<UserDto> getSortedUser(Pageable pageable);

    void deleteUser(String userId);

    UserDto getUser(String userId);

    UserDto getUserByEmail(String email);

    List<UserDto> getUsers();

    List<UserDto> searchUserName(String keyword);

}
