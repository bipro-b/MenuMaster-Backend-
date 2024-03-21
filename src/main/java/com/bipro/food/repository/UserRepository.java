package com.bipro.food.repository;

import com.bipro.food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String username);
}
// it provide crud operation and many more and also custom method