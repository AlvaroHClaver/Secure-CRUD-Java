package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.User;
import com.api.repository.UserRepository;

@RestController
public class UserController {

  @Autowired
  private UserRepository repository;

  @Autowired
  private PasswordEncoder encoder;
  
  @PostMapping("/register")
  private ResponseEntity<User> addUser(@RequestBody User user){

    user.setPassword(encoder.encode(user.getPassword()));

    return new ResponseEntity<User>(repository.save(user), HttpStatus.OK);
    
  }
}
