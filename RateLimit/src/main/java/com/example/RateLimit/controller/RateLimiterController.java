package com.example.RateLimit.controller;

import com.example.RateLimit.service.RateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    @Autowired
    private RateLimiterService rateLimiterService;

    @GetMapping("/test")
    public ResponseEntity<String> accessResource(@RequestHeader(value = "X-Client-Id") String clientId)
    {
        if(rateLimiterService.allowRequest(clientId))
        {
            return ResponseEntity.ok("Hello World!");
        }
        else
        {
            return ResponseEntity.status(429).body("Too Many requests");
        }
    }
}
