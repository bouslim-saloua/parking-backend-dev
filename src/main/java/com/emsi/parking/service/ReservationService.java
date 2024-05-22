package com.emsi.parking.service;

import com.emsi.parking.model.Reservation;
public interface ReservationService {
    Reservation ajouter(Reservation reservation) throws Exception;
    int nombreTotalReservations();
    int nombreTotalReservationsAnnulee();
    int nombreTotalReservationsEnCours();
}
