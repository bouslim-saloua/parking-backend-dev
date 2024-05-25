package com.emsi.parking.controller;

import com.emsi.parking.google.GoogleMapsService;
import com.emsi.parking.model.Parking;
import com.emsi.parking.service.ParkingService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    final ParkingService parkingService;
    
    @Autowired
    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }
    
    @GetMapping("/adresse/{adresse}")
    public String getNearbyParkings(@PathVariable String adresse){
        return googleMapsService.findNearbyParking(adresse);
    }
    
    @GetMapping("/allDisponible")
    public ResponseEntity<?> findAllDisponible(){
    return ResponseEntity.ok().body(parkingService.findAllDisponible());
    }
    
    @GetMapping("/locations")
    public ResponseEntity<?> getAllLocations() {
        return ResponseEntity.ok().body(parkingService.getAllAdresses());
    }

       @GetMapping("/secteurs/{secteurId}/parkings")
    public ResponseEntity<?> getParkingsBySecteur(@PathVariable Long secteurId) {
        return ResponseEntity.ok().body(parkingService.findBySecteur(secteurId));

    }
}
