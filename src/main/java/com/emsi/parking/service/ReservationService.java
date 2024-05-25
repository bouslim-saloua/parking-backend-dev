package com.emsi.parking.service;

import java.util.List;
import java.util.Optional;

import com.emsi.parking.model.Reservation;
public interface ReservationService {
Reservation ajouter(Reservation reservation) throws Exception;

void delete(Reservation r);

List<Reservation> findAll();

Optional<Reservation> findById(Long id);

void deleteById(Long id);

Reservation cancelReservation(Long reservationId) throws Exception;

Long countReservationsToday();


    int nombreTotalReservations();
    int nombreTotalReservationsAnnulee();
    int nombreTotalReservationsEnCours();
}
