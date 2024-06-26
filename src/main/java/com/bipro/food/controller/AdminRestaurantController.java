package com.bipro.food.controller;

import com.bipro.food.model.Restaurant;
import com.bipro.food.model.USER_ROLE;
import com.bipro.food.model.User;
import com.bipro.food.request.CreateRestaurantRequest;
import com.bipro.food.response.MessageResponse;
import com.bipro.food.service.IRestaurantService;
import com.bipro.food.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private IRestaurantService iRestaurantService;

    @Autowired
    private IUserService iUserService;



    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        if (user.getRole()!= USER_ROLE.ADMIN && user.getRole()!=USER_ROLE.RESTAURANT_OWNER){
            // If the user does not have the required role, return a forbidden response
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Restaurant restaurant = iRestaurantService.createRestaurant(req,user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        Restaurant restaurant = iRestaurantService.updateRestaurant(id,req);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable  Long id) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        iRestaurantService.deleteRestaurant(id);

        MessageResponse res =  new MessageResponse();
        res.setMessage("restaurant Deleted successfully");
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        Restaurant restaurant = iRestaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = iUserService.findUserByJwtToken(jwt);

        Restaurant restaurant = iRestaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


}
