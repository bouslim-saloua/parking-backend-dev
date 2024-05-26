package com.emsi.parking.service;

import java.util.List;
import java.util.Optional;

import com.emsi.parking.model.Reservation;
public interface ReservationService {
	void delete(Reservation r);
	List<Reservation> findAll();
	Optional<Reservation> findById(Long id);
	void deleteById(Long id);
	Long countReservationsToday();
	Reservation confirmerReservation(Long id) throws Exception;
	Reservation annulerReservation(Long id) throws Exception;
	Reservation ajouter(Reservation reservation) throws Exception;
}
