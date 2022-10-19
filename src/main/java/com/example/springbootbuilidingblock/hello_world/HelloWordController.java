package com.example.springbootbuilidingblock.hello_world;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld () {
        String message = "Hello Worlds";
        return "message";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world-bean")
    public UserDetailsModel helloWorldBean () {
        return new UserDetailsModel("james", "Obi", "Lagos");
    }
}
