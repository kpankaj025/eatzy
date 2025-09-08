package com.foodie.eatzy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foodie.eatzy.dto.UserDto;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto getById(String id);

    UserDto updateUser(UserDto userDto, String id);

    void deleteUser(String id);

    List<UserDto> getUserByName(String name);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUserByName(String keyword);

    Page<UserDto> getSortedUser(Pageable pageable);
}
