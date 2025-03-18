package com.att.tdp.popcorn_palace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/")
    public String HelloWorld() {
        return "Hello World!";
    }
}
