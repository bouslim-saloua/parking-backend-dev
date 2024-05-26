package com.emsi.parking.controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.emsi.parking.model.Reservation;

import com.emsi.parking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.emsi.parking.model.Reservation;
import com.emsi.parking.service.ReservationService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
	
    final ReservationService reservationService;
    
    @GetMapping("/nombre-total")
    public ResponseEntity<?> nombreTotalReservations() {
        return ResponseEntity.ok().body(reservationService.nombreTotalReservations());
    }

    @GetMapping("/annulee/nombre-total")
    public ResponseEntity<?> nombreTotalReservationsAnnulee() {
        return ResponseEntity.ok().body(reservationService.nombreTotalReservationsAnnulee());
    }

    @GetMapping("/en-cours/nombre-total")
    public ResponseEntity<?> nombreTotalReservationsEnCours() {
       return ResponseEntity.ok().body(reservationService.nombreTotalReservationsEnCours());
    }


	@PostMapping("/add")
	public ResponseEntity<?> reserverPlace(@RequestBody Reservation reservation) {
		try {
			Reservation newReservation = reservationService.ajouter(reservation);
			return ResponseEntity.ok().body(newReservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
	@PostMapping( "/save")
	public Reservation save(@RequestBody Reservation r) throws Exception {
		return reservationService.ajouter(r);

	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody Reservation r) {

		reservationService.delete(r);

    	reservationService.delete(r);
	}
	@GetMapping("")
	public List<Reservation> findAll() {
		return reservationService.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Reservation> findById(@PathVariable("id") Long id) {
		return reservationService.findById(id);
	}
	@DeleteMapping("/deletByid/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		reservationService.deleteById(id);
	}
	//afficher le nombre des reservations effectuees durant la journée 	
	@GetMapping("/reservations/countToday")
	public Long getCountToday() {
		return reservationService.countReservationsToday();
	}
	//confirmer une reservation 
	@PutMapping("/confirm/{id}")
	public ResponseEntity<Reservation> confirmerReservation(@PathVariable Long id) {
		try {
			Reservation reservation = reservationService.confirmerReservation(id);
			return ResponseEntity.ok(reservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	//annuler une reservation
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Reservation> annulerReservation(@PathVariable Long id) {
		try {
			Reservation reservation = reservationService.annulerReservation(id);
			return ResponseEntity.ok(reservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
  
}
