package com.foodie.eatzy.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.foodie.eatzy.dto.UserDto;
import com.foodie.eatzy.entity.User;
import com.foodie.eatzy.exception.ResourceNotFoundException;
import com.foodie.eatzy.repository.UserRepo;
import com.foodie.eatzy.service.UserService;
import com.foodie.eatzy.util.Helper;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        user.setId(Helper.uuid());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepo.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto getById(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        modelMapper.map(userDto, User.class);
        user.setId(id);
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userRepo.findByName(name);
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("no user found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> searchUserByName(String keyword) {
        List<User> users = userRepo.findByNameContaining(keyword);
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public Page<UserDto> getSortedUser(Pageable pageable) {

        Page<User> users = userRepo.findAll(pageable);
        return users.map(user -> modelMapper.map(user, UserDto.class));
    }

}
