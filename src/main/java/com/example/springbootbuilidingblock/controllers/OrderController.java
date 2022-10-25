package com.example.springbootbuilidingblock.controllers;

import com.example.springbootbuilidingblock.entities.Order;
import com.example.springbootbuilidingblock.entities.User;
import com.example.springbootbuilidingblock.exceptions.UserNameNotFoundException;
import com.example.springbootbuilidingblock.repository.OrderRepository;
import com.example.springbootbuilidingblock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
    // autowire the userRepository
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userId}/orders")
    public List<Order> getAllUserOrders(@PathVariable Long userId) throws UserNameNotFoundException {
        Optional<User> checkUser = userRepository.findById(userId);
        if (!checkUser.isPresent()) {
            throw new UserNameNotFoundException("User not found.");
        }
        return checkUser.get().getOrders();
    }

    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNameNotFoundException {
        Optional<User> checkUser = userRepository.findById(userId);
        if (!checkUser.isPresent()) {
            throw new UserNameNotFoundException("User not found.");
        }

        User user = checkUser.get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        Optional<Order> checkOrder = orderRepository.findById(orderId);
        if (checkOrder.isEmpty()) {
            throw new RuntimeException("Order Not Found");
        }
        return checkOrder.get();
    }
}
