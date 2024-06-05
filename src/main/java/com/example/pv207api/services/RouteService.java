package com.example.pv207api.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RouteService {

    public Map<String, String> calculateRoute(Map<String, Double> destination) {
        double lon1 = 34.0522;
        double lat1 = -40.2437;
        double lon2 = destination.get("lon");
        double lat2 = destination.get("lat");

        double R = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c; // Distance in kilometers

        return Map.of(
                "distance", String.format("%.2f km", distance),
                "duration", String.format("%.2f hours", distance / 60) // Assuming an average speed of 60 km/h
        );
    }
}
