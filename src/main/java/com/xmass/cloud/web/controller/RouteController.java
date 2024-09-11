package com.xmass.cloud.web.controller;

import com.xmass.cloud.domain.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<String> getRoute(@RequestParam String origin, @RequestParam String destination) {
        String recommendedRoute = routeService.getRecommendedRoute(origin, destination);
        return ResponseEntity.ok(recommendedRoute);
    }
}
