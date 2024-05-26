package com.emsi.parking.service.impl;

import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Reservation;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.repository.ParkingRepository;
import com.emsi.parking.repository.ReservationRepository;
import com.emsi.parking.repository.UtilisateurRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emsi.parking.model.Place;
import com.emsi.parking.repository.PlaceRepository;

import com.emsi.parking.service.ReservationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{


	final ParkingRepository parkingRepository;
	final PlaceRepository placeRepository;
	final UtilisateurRepository utilisateurRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public Reservation ajouter(Reservation reservation) throws Exception {
		// Vérification de l'existence du parking
		Parking parking = parkingRepository.findById(reservation.getPlace().getParking().getId()).orElse(null);
		if (parking == null) {
			throw new Exception("Le parking spécifié n'existe pas");
		}

		// Vérification de l'existence de la place de parking
		Place place = placeRepository.findById(reservation.getPlace().getId()).orElse(null);
		if (place == null) {
			throw new Exception("La place spécifiée n'existe pas");
		}

		// Vérification de la disponibilité de la place de parking
		if (place.isReserve()) {
			throw new Exception("La place spécifiée n'est pas disponible");
		}

		// Vérification des conflits de réservation pour la période spécifiée
		List<Reservation> existingReservations = reservationRepository.findConflictingReservations(
				reservation.getPlace().getId(),
				reservation.getDateReservation(),
				reservation.getHeureEntree(),
				reservation.getHeureSortie()
				);

		if (!existingReservations.isEmpty()) {
			throw new Exception("La place est déjà réservée pour la période spécifiée");
		}

		// Effectuer la réservation
		place.setReserve(true);
		placeRepository.save(place);
		return reservationRepository.save(reservation);
	}

	//confirmation de la réservation
	public Reservation confirmerReservation(Long reservationId) throws Exception {
		Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
		if (!reservationOpt.isPresent()) {
			throw new Exception("Reservation non trouvée");
		}

		Reservation reservation = reservationOpt.get();
		reservation.setStatus("confirmée");
		return reservationRepository.save(reservation);
	}

	// annuler une reservation 

	public Reservation annulerReservation(Long reservationId) throws Exception {
		Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
		if (!reservationOpt.isPresent()) {
			throw new Exception("Reservation non trouvée");
		}

		Reservation reservation = reservationOpt.get();
		reservation.setStatus("annulée");
		return reservationRepository.save(reservation);
	}
	//afficher le nombre des reservations effectuees durant la journée 
	public Long countReservationsByDate(Date date) {
		return reservationRepository.countByDateReservation(date);
	}
	public Long countReservationsToday() {
		Date today = new Date();
		return countReservationsByDate(today);
	}

	@Override
	public void delete(Reservation r) {
		reservationRepository.delete(r);		
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();

	}

	@Override
	public Optional<Reservation> findById(Long id) {
		return reservationRepository.findById(id);

	}

	@Override
	public void deleteById(Long id) {
		reservationRepository.deleteById(id);		
	}

}
