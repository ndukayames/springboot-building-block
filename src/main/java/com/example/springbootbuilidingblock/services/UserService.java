package com.example.springbootbuilidingblock.services;


import com.example.springbootbuilidingblock.entities.User;
import com.example.springbootbuilidingblock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// declare @Service annotation
@Service
public class UserService {

    // autowire the userRepository
    @Autowired
    private UserRepository userRepository;

    // getAllUsers Method
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // create user service
    public User createUser(User user) throws DuplicateUserException {
        User findUser = findUserByUsername(user.getUsername());
        System.out.println(findUser);
        if (findUser != null) {
            throw new DuplicateUserException("This user already exists");
        } else {
            return userRepository.save(user);
        }
    }

    // get user by ID
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User does not exist.");
        }
    }

    // update user by id
    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> userCheck = userRepository.findById(id);
        if (userCheck.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User does not exist.");
        }

    }

    // delete user by id
    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    // find user by username
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}