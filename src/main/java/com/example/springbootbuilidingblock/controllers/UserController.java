package com.example.springbootbuilidingblock.controllers;

import com.example.springbootbuilidingblock.entities.User;
import com.example.springbootbuilidingblock.exceptions.DuplicateUserException;
import com.example.springbootbuilidingblock.exceptions.UserNotFoundException;
import com.example.springbootbuilidingblock.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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
    public User createUser(@RequestBody User user) {
        try {
            return userService.createUser(user);
        } catch (DuplicateUserException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }

    }

    // get user by id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
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
    public User findUserByUsername(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        System.out.println(user);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}
