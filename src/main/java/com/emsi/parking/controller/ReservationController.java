package com.emsi.parking.controller;

import com.emsi.parking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


}
