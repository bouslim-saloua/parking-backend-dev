package com.emsi.parking.service.impl;

import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Reservation;
import com.emsi.parking.repository.ParkingRepository;
import com.emsi.parking.repository.ReservationRepository;
import com.emsi.parking.repository.UtilisateurRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emsi.parking.model.Place;
import com.emsi.parking.repository.PlaceRepository;

import com.emsi.parking.service.ReservationService;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{


	final ParkingRepository parkingRepository;
	final PlaceRepository placeRepository;
	final UtilisateurRepository utilisateurRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation ajouter(Reservation reservation) throws Exception{
		Reservation reservationFromDB = reservationRepository.findById(reservation.getId()).orElse(null);
		//si une réservation avec l id reservation.getId() existe déjà dans la base de données on doit générer une exception
		if(reservationFromDB != null ) throw new Exception("LA réservation avec l'id "+reservationFromDB.getId()+" existe déjà");
		//vérification du parking
		Parking parkingDB = parkingRepository.findById(reservation.getPlace().getParking().getId()).orElse(null);
		Place placeFromDB = placeRepository.findById(reservation.getPlace().getId()).orElse(null);
		if(placeFromDB == null){
			throw new Exception("La place que vous voulez réserver est introuvable");
		}else if(placeFromDB.isReserve()){
			throw new Exception("La place que vous voulez réserver n'est pas disponible");
		}
		if(parkingDB != null){
			if(parkingDB.getStatus().equals("rempli")) throw new Exception("Le parking que vous essayez à réserver est rempli ");
		}else throw new Exception("Parking introuvable");
		//effectuer une reservation dans une place disponible et dans une période spécifiée

		/*List<Reservation> existingReservations = reservationRepository.findByPlaceIdAndDateEntreeAndDateSortieAndHeureEntreeAndHeureSortieBetween(
				reservation.getPlace().getId(), 
				reservation.getDateEntree(), 
				reservation.getDateSortie(), 
				reservation.getHeureEntree(), 
				reservation.getHeureSortie()
				);

		if (!existingReservations.isEmpty()) {
			throw new Exception("La place est déjà réservée pour la période spécifiée");
		}*/

		//confirmation de la réservation
		//Paiement
		//confirmation de paiement
		//utilisation du place de parking


		return reservationRepository.save(reservation);
	}

	// annuler une reservation 

	@Override
	public Reservation cancelReservation(Long reservationId) throws Exception {
		Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
		if (!reservationOpt.isPresent()) {
			throw new Exception("Réservation non trouvée avec l'identifiant fourni");
		}
		Reservation reservation = reservationOpt.get();
		reservation.setStatus("annulée");
		reservationRepository.save(reservation);

		return reservation;
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
