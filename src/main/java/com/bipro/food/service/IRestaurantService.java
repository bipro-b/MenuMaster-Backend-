package com.bipro.food.service;

import com.bipro.food.dto.RestaurantDto;
import com.bipro.food.model.Restaurant;
import com.bipro.food.model.User;
import com.bipro.food.request.CreateRestaurantRequest;

import java.util.List;

public interface IRestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedResturant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavorirtes(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;



}
