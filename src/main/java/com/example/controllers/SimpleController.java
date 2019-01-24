package com.example.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public HttpEntity<Map<String,Object>> homePage(Model model) {
        Map<String, Object> mapResponse = Collections.singletonMap("appName",appName);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
