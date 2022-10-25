package com.example.springbootbuilidingblock.controllers;

import com.example.springbootbuilidingblock.entities.User;
import com.example.springbootbuilidingblock.exceptions.DuplicateUserException;
import com.example.springbootbuilidingblock.exceptions.UserNameNotFoundException;
import com.example.springbootbuilidingblock.exceptions.UserNotFoundException;
import com.example.springbootbuilidingblock.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class UserController {

    // autowire user service
    @Autowired
    private UserService userService;

    // get all users endpoint
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // create a user endpoint
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<User>(headers, HttpStatus.CREATED);
        } catch (DuplicateUserException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }

    }

    // get user by id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // update user by id
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    // delete user by id
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    // find user by username
    @GetMapping("users/byusername/{username}")
    public User findUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UserNameNotFoundException("username doesn't match any account");
        }
        return user;
    }
}
