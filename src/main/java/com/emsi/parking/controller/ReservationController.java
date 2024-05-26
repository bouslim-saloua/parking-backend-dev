package com.emsi.parking.controller;


import java.util.List;
import java.util.Optional;

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

import com.emsi.parking.model.Reservation;
import com.emsi.parking.service.ReservationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	@Autowired
	private ReservationService reservationServices;


	@PostMapping("/add")
	public ResponseEntity<?> reserverPlace(@RequestBody Reservation reservation) {
		try {
			Reservation newReservation = reservationServices.ajouter(reservation);
			return ResponseEntity.ok().body(newReservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody Reservation r) {
		reservationServices.delete(r);
	}
	@GetMapping("")
	public List<Reservation> findAll() {
		return reservationServices.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Reservation> findById(@PathVariable("id") Long id) {
		return reservationServices.findById(id);
	}
	@DeleteMapping("/deletByid/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		reservationServices.deleteById(id);
	}
	//afficher le nombre des reservations effectuees durant la journée 	
	@GetMapping("/reservations/countToday")
	public Long getCountToday() {
		return reservationServices.countReservationsToday();
	}
	//confirmer une reservation 
	@PutMapping("/confirm/{id}")
	public ResponseEntity<Reservation> confirmerReservation(@PathVariable Long id) {
		try {
			Reservation reservation = reservationServices.confirmerReservation(id);
			return ResponseEntity.ok(reservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	//annuler une reservation
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Reservation> annulerReservation(@PathVariable Long id) {
		try {
			Reservation reservation = reservationServices.annulerReservation(id);
			return ResponseEntity.ok(reservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
