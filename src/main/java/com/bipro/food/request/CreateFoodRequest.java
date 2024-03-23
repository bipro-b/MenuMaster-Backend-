package com.bipro.food.request;

import com.bipro.food.model.Category;
import com.bipro.food.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private int price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasional;

    private List<IngredientsItem> ingredients;

}
