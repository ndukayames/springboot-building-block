package com.example.springbootbuilidingblock.controllers;

import com.example.springbootbuilidingblock.entities.User;
import com.example.springbootbuilidingblock.exceptions.UserNotFoundException;
import com.example.springbootbuilidingblock.services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
    @Autowired
    private UserService userService;

    // get user by id
    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            User user = userService.getUserById(id);
            Set<String> fields = new HashSet<String>();
            fields.add("id");
            fields.add("username");
            fields.add("ssn");
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // get user by id with dynamic filtering via query param
//    @GetMapping("/params/{id}")
//    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {
//        System.out.println(12345678);
//        System.out.println(fields);
//        try {
//            User user = userService.getUserById(id);
//            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
//            MappingJacksonValue mapper = new MappingJacksonValue(user);
//            mapper.setFilters(filterProvider);
//
//            return mapper;
//        } catch (UserNotFoundException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//        }
//    }
}
