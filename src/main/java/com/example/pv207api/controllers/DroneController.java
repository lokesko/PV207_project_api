package com.example.pv207api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pv207api.services.DroneService;
import com.example.pv207api.services.RouteService;
import com.example.pv207api.services.ValidationService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DroneController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private DroneService droneService;

    @Autowired
    private ValidationService validationService;

    // Default API endpoint: GET /api
    @GetMapping
    public String api() {
        return "Hello from the API!";
    }

    // Endpoint calculate-route: POST /api/calculate-route
    @PostMapping("/calculate-route")
    public Map<String, String> calculateRoute(@RequestBody Map<String, Double> destination) {
        return routeService.calculateRoute(destination);
    }

    // Endpoint available-drones: POST /api/available-drones
    @PostMapping("/available-drones")
    public Object availableDrones(@RequestBody Map<String, Integer> requestBody) {
        Integer stationId = requestBody.get("stationId");
        var validation = validationService.validateId(stationId);
        if (!validation.isValid) {
            return Map.of("error", validation.error);
        }
        return droneService.getAvailableDrones(stationId);
    }

    // Endpoint request-maintenance: POST /api/request-maintenance
    @PostMapping("/request-maintenance")
    public Object requestMaintenance(@RequestBody Map<String, Object> requestBody) {
        var validation = validationService.validateMaintenanceRequest(requestBody);
        if (!validation.isValid) {
            return Map.of("error", validation.error);
        }
        Integer droneId = (Integer) requestBody.get("drone_id");
        String problemType = (String) requestBody.get("problem_type");
        return Map.of("message", "Maintenance team for drone " + droneId + " with problem: " + problemType + " is on the way.");
    }
}
