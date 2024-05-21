package com.emsi.parking.controller;

import com.emsi.parking.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/place")
public class PlaceController {
    final PlaceService placeService;
    
    @Autowired
    public PlaceController(PlaceService placeService){
        this.placeService = placeService;
    }
    
    
    @GetMapping("/allDisponible")
    public ResponseEntity<?> findAllDisponiblePlaces(){
    return ResponseEntity.ok().body(placeService.findAllDisponiblePlaces());
    }
    
    @GetMapping("/allReserved")
    public ResponseEntity<?> findAllReservedPlaces(){
        return ResponseEntity.ok().body(placeService.findAllReservedPlaces());
    }
    
     @GetMapping("/all-place/by-parking/{parkingId}")
    public ResponseEntity<?> findByParkingId(@PathVariable long parkingId){
        return ResponseEntity.ok().body(placeService.findByParkingId(parkingId));
    }
  
   

    
}
