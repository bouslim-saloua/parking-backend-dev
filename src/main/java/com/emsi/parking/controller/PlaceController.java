package com.emsi.parking.controller;
import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Place;
import com.emsi.parking.repository.ParkingRepository;
import com.emsi.parking.repository.PlaceRepository;
import com.emsi.parking.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/place")
public class PlaceController {
    final PlaceService placeService;
    
    @Autowired
    public PlaceController(PlaceService placeService){
        this.placeService = placeService;
    }
    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private PlaceRepository placeRepository; 
    
    
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
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

