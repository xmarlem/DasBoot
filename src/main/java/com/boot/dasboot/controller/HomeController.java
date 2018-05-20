package com.boot.dasboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/Home")
    public String home(){
        return "Home page";
    }

}
