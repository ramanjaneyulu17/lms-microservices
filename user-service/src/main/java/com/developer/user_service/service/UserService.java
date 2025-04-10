package com.developer.user_service.service;

import com.developer.user_service.dao.UserDao;
import com.developer.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public ResponseEntity<Optional<User>> getUserById(Integer id) {
        Optional<User> findUser = userDao.findById(Long.valueOf(id));
        if (findUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    public ResponseEntity<String> register(User user) {
        if (userDao.existsByUserName(user.getUserName())) {
            return new ResponseEntity<>("User already exist", HttpStatus.ALREADY_REPORTED);
        } else {
            user.setUserPassword(encoder.encode(user.getUserPassword()));
            userDao.save(user);
            return new ResponseEntity<>("Registration success", HttpStatus.CREATED);
        }
    }

    public String authenticateUser(String username, String password) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        if(authentication.isAuthenticated()){
            return jwtService.generateJwtToken(username);
        }
        return "Not authenticated";
    }
}
