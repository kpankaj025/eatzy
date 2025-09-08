package com.foodie.eatzy.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodie.eatzy.dto.FileData;
import com.foodie.eatzy.dto.RestaurantDto;
import com.foodie.eatzy.service.RestaurantService;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // add

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')") // method level security
    public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody RestaurantDto restaurantDto) {
        RestaurantDto restaurant = restaurantService.save(restaurantDto);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.getAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // getById

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable("id") String id) {

        RestaurantDto restaurant = restaurantService.getById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // find by name
    @GetMapping("/name/{name}")
    public ResponseEntity<RestaurantDto> findByName(@PathVariable("name") String name) {
        RestaurantDto restaurant = restaurantService.findByName(name)
                .orElseThrow(() -> new RuntimeException("restaurant not found"));
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // find by is open

    @GetMapping("/isOpen/{isOpen}")
    public ResponseEntity<List<RestaurantDto>> findByIsOpen(@PathVariable("isOpen") boolean isOpen) {
        List<RestaurantDto> restaurants = restaurantService.findByIsOpen(isOpen);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable("id") String id,
            @RequestBody RestaurantDto restaurantDto) {
        RestaurantDto updatedRestaurant = restaurantService.update(id,
                restaurantDto);
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    // file upload

    @PostMapping("/upload/{restaurantId}")
    public ResponseEntity<?> uploadFile(@RequestParam("banner") MultipartFile banner, @PathVariable String restaurantId)
            throws IOException {

        String pathFile = "uploads/restaurantbanner/";
        RestaurantDto restaurantDto = restaurantService.uploadFile(banner, pathFile, restaurantId);

        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);

    }

    // @Value("${restaurant.banner.path}")
    // private String bannerFolderPath;

    // file serve
    @GetMapping("/banner/{restaurantId}")
    public ResponseEntity<Resource> serveFile(@PathVariable String restaurantId) throws MalformedURLException {

        RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
        String fullPath = "uploads/restaurantbanner/" + restaurantDto.getBanner();

        Path path = Path.of(fullPath);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or detect dynamically
                .body(resource);

    }

}
