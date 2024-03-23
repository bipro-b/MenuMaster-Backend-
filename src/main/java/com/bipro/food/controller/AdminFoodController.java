package com.bipro.food.controller;


import com.bipro.food.model.Food;
import com.bipro.food.model.Restaurant;
import com.bipro.food.model.User;
import com.bipro.food.request.CreateFoodRequest;
import com.bipro.food.response.MessageResponse;
import com.bipro.food.service.IFoodService;
import com.bipro.food.service.IRestaurantService;
import com.bipro.food.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private IFoodService iFoodService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRestaurantService iRestaurantService;


    @PostMapping()
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization")
                                           String jwt) throws Exception{
        User user = iUserService.findUserByJwtToken(jwt);
        Restaurant restaurant = iRestaurantService.findRestaurantById(req.getRestaurantId());
        Food food = iFoodService.createFood(req,req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization")
                                           String jwt) throws Exception{
        User user = iUserService.findUserByJwtToken(jwt);

        iFoodService.deleteFood(id);
        MessageResponse message = new MessageResponse();
        message.setMessage("Succesfully delete");

        return new ResponseEntity<>(message,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvaibilityStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization")
                                                      String jwt) throws Exception{
        User user = iUserService.findUserByJwtToken(jwt);

        Food updatedFood = iFoodService.updateAvailibilityStatus(id);



        return new ResponseEntity<>(updatedFood,HttpStatus.CREATED);

    }




}
