package com.foodie.eatzy.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodie.eatzy.dto.FileData;
import com.foodie.eatzy.dto.RestaurantDto;
import com.foodie.eatzy.dto.UserDto;
import com.foodie.eatzy.entity.Restaurant;
import com.foodie.eatzy.exception.ResourceNotFoundException;
import com.foodie.eatzy.repository.RestaurantRepo;
import com.foodie.eatzy.repository.UserRepo;
import com.foodie.eatzy.service.RestaurantService;
import com.foodie.eatzy.util.Helper;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private ModelMapper modelMapper;
    private RestaurantRepo restaurantRepo;
    private final UserRepo userRepo;
    private final FileUploadService fileUploadService;

    public RestaurantServiceImpl(ModelMapper modelMapper, RestaurantRepo restaurantRepo, UserRepo userRepo,
            FileUploadService fileUploadService) {
        this.modelMapper = modelMapper;
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public RestaurantDto save(RestaurantDto restaurantDto) {
        // Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        // restaurant.setId(Helper.uuid());
        // restaurant.setOwner(
        // userRepo.findById(restaurantDto.getUserId())
        // .orElseThrow(() -> new ResourceNotFoundException("user not found")));
        // Restaurant savedRestaurant = restaurantRepo.save(restaurant);

        // RestaurantDto res = modelMapper.map(savedRestaurant, RestaurantDto.class);
        // res.setUserDto(modelMapper.map(savedRestaurant.getUser(), UserDto.class));

        // return res;
        return null;
    }

    @Override
    public List<RestaurantDto> getAll() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDto> restaurantDtos = restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).toList();
        return restaurantDtos;

    }

    @Override
    public RestaurantDto getById(String id) {
        // Restaurant restaurant = restaurantRepo.findById(id)
        // .orElseThrow(() -> new ResourceNotFoundException("restaurant not found"));
        // RestaurantDto sR = modelMapper.map(restaurant, RestaurantDto.class);
        // sR.setUserDto(modelMapper.map(restaurant.getUser(), UserDto.class));
        // return sR;
        return null;
    }

    @Override
    public RestaurantDto update(String id, RestaurantDto restaurantDto) {
        // Restaurant restaurant = restaurantRepo.findById(id)
        // .orElseThrow(() -> new ResourceNotFoundException("restaurant not found"));
        // modelMapper.map(restaurantDto, Restaurant.class);
        // restaurant.setId(id);
        // Restaurant updatedRestaurant = restaurantRepo.save(restaurant);
        // RestaurantDto rD = modelMapper.map(updatedRestaurant, RestaurantDto.class);
        // rD.setUserDto(modelMapper.map(updatedRestaurant.getUser(), UserDto.class));
        // return rD;

        return null;
    }

    @Override
    public void delete(String id) {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("restaurant not found"));
        restaurantRepo.delete(restaurant);
    }

    @Override
    public Optional<RestaurantDto> findByName(String name) {
        Optional<Restaurant> restaurant = restaurantRepo.findByName(name);
        if (restaurant.isPresent()) {
            return Optional.of(modelMapper.map(restaurant.get(), RestaurantDto.class));
        } else {
            return Optional.empty();
        }

    }

    @Override
    public List<RestaurantDto> findByIsOpen(boolean isOpen) {
        // List<Restaurant> restaurants = restaurantRepo.findByIsOpen(isOpen);
        // return restaurants.stream().map(restaurant -> modelMapper.map(restaurant,
        // RestaurantDto.class)).toList();
        return null;
    }

    // @Value("${restaurant.banner.path}")
    // private String folderPath;

    @Override
    public RestaurantDto uploadFile(MultipartFile file, String pathFile, String restaurantId) throws IOException {

        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = new Date().getTime() + fileExtension;

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("restaurant not found"));

        FileData fileData = fileUploadService.uploadFile(file, pathFile + newFileName);

        restaurant.setBannerImageUrl(fileData.getFileName());
        Restaurant updatedRestaurant = restaurantRepo.save(restaurant);

        return modelMapper.map(updatedRestaurant, RestaurantDto.class);

    }

}
