package com.cb.demo.demo.controller;

import jakarta.annotation.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/demo-app")
public class TestController {


    @GetMapping("/get")
    public ResponseEntity<String> getMessage(){
        return new ResponseEntity<>("Hello CB", HttpStatus.OK);
    }

}
