package com.emsi.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Reservation;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value="SELECT COUNT(*) FROM Reservation")
    int nombreTotalReservations();
    
    @Query(value="SELECT COUNT(*) FROM Reservation R where R.status = 'annulée'")
    int nombreTotalReservationsAnnulee();
    
    @Query(value="SElECT COUNT(*) FROM Reservation R WHERE R.status = 'en cours'")
    int nombreTotalReservationsEnCours();
}
