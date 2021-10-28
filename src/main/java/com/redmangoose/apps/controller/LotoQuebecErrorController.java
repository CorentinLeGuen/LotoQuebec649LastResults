package com.redmangoose.apps.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LotoQuebecErrorController implements ErrorController {
    @GetMapping(value = "/error", produces = "application/json")
    public Map<String, String> error() {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "400");
        errors.put("message", "An error occurred. Maybe the endpoint you are looking for doesn't exists.");
        errors.put("timestamp", LocalDateTime.now().toString());
        return errors;
    }
}
