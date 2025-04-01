package com.developer.user_service.service;

import com.developer.user_service.dao.UserDao;
import com.developer.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Optional<User>> getUserById(Integer id) {
        Optional<User> findUser=userDao.findById(Long.valueOf(id));
        if(findUser.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }

    public ResponseEntity<String> register(User user) {
        if(userDao.existsByUserName(user.getUserName())){
            return new ResponseEntity<>("User already exist",HttpStatus.ALREADY_REPORTED);
        }else {
            userDao.save(user);
            return new ResponseEntity<>("Registration success",HttpStatus.CREATED);
        }
    }

    public ResponseEntity<String> authenticateUser(String username, String password) {
        Optional<User> user=userDao.findByUserName(username);
        if(user.isPresent()  && password.equals(user.get().getUserPassword())){
            return new ResponseEntity<>("Login Success",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Miss match username or password",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
