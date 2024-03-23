package com.bipro.food.service;

import com.bipro.food.model.Category;
import com.bipro.food.model.Food;
import com.bipro.food.model.Restaurant;
import com.bipro.food.request.CreateFoodRequest;

import java.util.List;

public interface IFoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegitarian,
                                         boolean isNonVeg,
                                         boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailibilityStatus(Long foodId) throws Exception;



}
