package com.example.project1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstEndpoint {
    @GetMapping("/")
    public String sayHello(){
        return  "Hello";
    }
}
// maven is used to manage dependencies and build tools
