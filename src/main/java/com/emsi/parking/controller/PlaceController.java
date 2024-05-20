package com.emsi.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Place;
import com.emsi.parking.repository.ParkingRepository;
import com.emsi.parking.repository.PlaceRepository;
import com.emsi.parking.service.PlaceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/place")
public class PlaceController {
	final PlaceService placeService ;
	private final ParkingRepository parkingRepository;

	public PlaceController(PlaceService placeService, ParkingRepository parkingRepository) {
        this.placeService = placeService;
        this.parkingRepository = parkingRepository;
	}
	@Autowired
	private PlaceRepository placeRepository;

	@PostMapping("/add")
	public ResponseEntity<String> createPlace(@RequestBody Place place) {
	    if (place.getParking() == null || place.getParking().getId() == null) {
	        return ResponseEntity.badRequest().body("Parking ID is required");
	    }
	    // Retrieve the parking from the database
	    Parking parking = parkingRepository.findById(place.getParking().getId())
	                        .orElseThrow(() -> new IllegalArgumentException("Parking with ID " + place.getParking().getId() + " does not exist"));

	     // Set the parking for the place
	    place.setParking(parking);
	    // Save the place
	    placeRepository.save(place);
	    return ResponseEntity.status(HttpStatus.CREATED).body("Place added successfully with parking ID " + parking.getId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlace(@PathVariable Long id) {
		try {
			placeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	 @PutMapping("/update/{id}")
	    public ResponseEntity<?> updatePlace(@PathVariable Long id, @RequestBody Place updatedPlace) {
	        try {
	            placeService.updatePlace(id, updatedPlace); 
	            return ResponseEntity.ok().body("Place mise à jour avec succès");
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	 @GetMapping("/{id}")
	    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
	        try {
	            Place place = placeService.getPlaceById(id);
	            return ResponseEntity.ok(place);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	 @GetMapping("/all")
	    public ResponseEntity<List<Place>> getAllPlaces() {
	        try {
	            List<Place> places = placeService.getAllPlaces();
	            return ResponseEntity.ok(places);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }


}
