package com.example.pv207api.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DroneService {

    public Map<String, Integer> getAvailableDrones(int stationId) {
        int availableDrones = (int) (Math.random() * 10) + 1;
        return Map.of("available_drones", availableDrones);
    }
}
