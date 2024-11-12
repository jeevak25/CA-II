package com.Jivak.joblisting.controller;


import com.Jivak.joblisting.model.User;
import com.Jivak.joblisting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup( @RequestBody User user) {
        try {
            User createdUser = userService.signup(user);
            createdUser.setPassword(null); // Exclude password from response
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User authenticatedUser = userService.login(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(authenticatedUser); // User logged in successfully
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


//    @PostMapping("/signup")
//    public User signup(@RequestBody User user) {
//        try {
//            return userService.signup(user);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

//    @PostMapping("/login")
//    public User login(@RequestBody User user) {
//        try {
//            return userService.login(user.getUsername(), user.getPassword());
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}
