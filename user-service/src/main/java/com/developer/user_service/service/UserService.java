package com.developer.user_service.service;

import com.developer.user_service.dao.UserDao;
import com.developer.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userDao.findById(Long.valueOf(id));
    }

    public String register(User user) {
        if(userDao.existsByUserName(user.getUserName())){
            return "User Exist";
        }else {
            userDao.save(user);
            return "success";
        }
    }

    public String authenticateUser(String username, String password) {
        Optional<User> user=userDao.findByUserName(username);
        if(user.isPresent()  && password.equals(user.get().getUserPassword())){
            return "Login Success";
        }else {
            return "Miss match username or password";
        }
    }
}
