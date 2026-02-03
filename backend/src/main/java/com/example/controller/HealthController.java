package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public Map<String, Object> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "Spring Boot 后端服务运行正常");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

}
