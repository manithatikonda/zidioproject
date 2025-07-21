package com.zidioconnect.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/secret")
    public String secretTest() {
        return " You accessed a protected API!";
    }
}
