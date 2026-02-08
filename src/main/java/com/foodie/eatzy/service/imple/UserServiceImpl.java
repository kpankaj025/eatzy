package com.foodie.eatzy.service.imple;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodie.eatzy.dto.UserDto;
import com.foodie.eatzy.entity.User;
import com.foodie.eatzy.exception.ResourceNotFoundException;
import com.foodie.eatzy.repository.UserRepository;
import com.foodie.eatzy.service.UserService;
import com.foodie.eatzy.util.Helper;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto saveUser(UserDto user) {
        user.setId(Helper.uuid());
        User savedUser = userRepository.save(modelMapper.map(user, User.class));
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user, String userId) {
        user.setId(null);
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        BeanUtils.copyProperties(user, user1);
        User savedUser = userRepository.save(user1);
        return modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public void deleteUser(String userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepository.deleteById(userId);

    }

    @Override
    public UserDto getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);

    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
    }

    @Override
    public List<UserDto> searchUserName(String keyword) {

        List<User> users = userRepository.findByNameContaining(keyword);
        return users.stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> lists = userRepository.findByName(name);
        return lists.stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
    }

    @Override
    public UserDto getById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not  found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Page<UserDto> getSortedUser(Pageable pageable) {
        Page<User> pages = userRepository.findAll(pageable);
        return pages.map(u -> modelMapper.map(u, UserDto.class));
    }
}