package com.foodie.eatzy.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.foodie.eatzy.dto.FileData;
import com.foodie.eatzy.dto.RestaurantDto;

public interface RestaurantService {
    // add

    RestaurantDto save(RestaurantDto restaurantDto);

    // get all
    List<RestaurantDto> getAll();

    // get by id
    RestaurantDto getById(String id);

    // update
    RestaurantDto update(String id, RestaurantDto restaurantDto);

    // delete
    void delete(String id);

    // find by name
    Optional<RestaurantDto> findByName(String name);

    // find by is open

    List<RestaurantDto> findByIsOpen(boolean isOpen);

    // upload file

    public RestaurantDto uploadFile(MultipartFile file, String pathFile, String restaurantId) throws IOException;

}
