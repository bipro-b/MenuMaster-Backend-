package com.bipro.food.controller;

import com.bipro.food.dto.RestaurantDto;
import com.bipro.food.model.Restaurant;
import com.bipro.food.model.User;
import com.bipro.food.request.CreateRestaurantRequest;
import com.bipro.food.service.IRestaurantService;
import com.bipro.food.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class CustomerRestaurantController {

    @Autowired
    private IRestaurantService iRestaurantService;

    @Autowired
    private IUserService iUserService;



    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt ,
            @RequestParam String keyword
            ) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = iRestaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = iRestaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        Restaurant restaurant = iRestaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorite")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        RestaurantDto restaurant = iRestaurantService.addToFavorirtes(id,user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }




}
