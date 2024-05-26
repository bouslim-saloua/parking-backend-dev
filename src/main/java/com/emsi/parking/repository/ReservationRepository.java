package com.emsi.parking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Reservation;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Long countByDateReservation(Date date);

    @Query("SELECT r FROM Reservation r WHERE r.place.id = :placeId AND r.dateReservation = :dateReservation AND " +
           "((:heureEntree BETWEEN r.heureEntree AND r.heureSortie) OR (:heureSortie BETWEEN r.heureEntree AND r.heureSortie) OR " +
           "(r.heureEntree BETWEEN :heureEntree AND :heureSortie) OR (r.heureSortie BETWEEN :heureEntree AND :heureSortie))")
    List<Reservation> findConflictingReservations(Long placeId, Date dateReservation, int heureEntree, int heureSortie);

    @Query(value="SELECT COUNT(*) FROM Reservation")
    int nombreTotalReservations();
    
    @Query(value="SELECT COUNT(*) FROM Reservation R where R.status = 'annulée'")
    int nombreTotalReservationsAnnulee();
    
    @Query(value="SElECT COUNT(*) FROM Reservation R WHERE R.status = 'en cours'")
    int nombreTotalReservationsEnCours();

}
