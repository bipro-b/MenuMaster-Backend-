package com.bipro.food.controller;

import com.bipro.food.model.Food;
import com.bipro.food.model.Restaurant;
import com.bipro.food.model.User;
import com.bipro.food.request.CreateFoodRequest;
import com.bipro.food.service.IFoodService;
import com.bipro.food.service.IRestaurantService;
import com.bipro.food.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private IFoodService iFoodService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRestaurantService iRestaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                           @RequestHeader("Authorization")
                                           String jwt) throws Exception{
        User user = iUserService.findUserByJwtToken(jwt);
        List<Food> food = iFoodService.searchFood(keyword);

        return new ResponseEntity<>(food, HttpStatus.OK);

    }


    @GetMapping("/restaruan/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
                                                 @RequestParam boolean vagiarian,
                                                 @RequestParam boolean  sessional,
                                                 @RequestParam boolean nonveg,
                                                 @RequestParam(required = false) String food_category,
                                                 @PathVariable Long id,
                                                 @RequestHeader("Authorization")

                                                 String jwt) throws Exception{
        User user = iUserService.findUserByJwtToken(jwt);
        List<Food> food = iFoodService.getRestaurantsFood(id,vagiarian,nonveg,sessional,food_category);

        return new ResponseEntity<>(food, HttpStatus.OK);

    }





}
