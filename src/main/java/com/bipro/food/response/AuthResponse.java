package com.bipro.food.response;

import com.bipro.food.model.USER_ROLE;
import lombok.Data;


@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private USER_ROLE userRole;


}
