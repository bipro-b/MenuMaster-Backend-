package com.bipro.food.service;

import com.bipro.food.config.JwtProvider;
import com.bipro.food.model.User;
import com.bipro.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{


    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String  email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user= userRepository.findByEmail(email);;
        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }
}
