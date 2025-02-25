package com.developer.user_service.dao;

import com.developer.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String username);
}

