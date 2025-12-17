package com.example.demo.controller;
import org.springframework.web.blind.annotation.*;
@RestController
public class basics{
    @GetMapping("/home")
    public String name(){
        return "Hello world";
    }
}