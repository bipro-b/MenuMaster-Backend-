package com.bipro.food.service;

import com.bipro.food.model.User;

public interface IUserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

}
