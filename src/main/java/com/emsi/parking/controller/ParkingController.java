package com.emsi.parking.controller;

import com.emsi.parking.google.GoogleMapsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Cette end-point permet de gérer les parkings")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Autowired
    private GoogleMapsService googleMapsService;
    
    @GetMapping("/adresse/{adresse}")
    public String getNearbyParkings(@PathVariable String adresse){
        return googleMapsService.findNearbyParking(adresse);
    }
}
