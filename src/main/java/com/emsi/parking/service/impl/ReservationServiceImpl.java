package com.emsi.parking.service.impl;

import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Reservation;
import com.emsi.parking.repository.ParkingRepository;
import com.emsi.parking.repository.ReservationRepository;
import com.emsi.parking.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import com.emsi.parking.model.Place;
import com.emsi.parking.repository.PlaceRepository;

import com.emsi.parking.service.ReservationService;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{

    final  ReservationRepository reservationRepository;
    final ParkingRepository parkingRepository;
    final PlaceRepository placeRepository;
    final UtilisateurRepository utilisateurRepository;
    
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
        //choix du place et disponibilité (choix de l'emplacement
        //selection des dates et heures d'entrée et sortie
        //confirmation de la réservation
       //Paiement
        //confirmation de paiement
        //utilisation du place de parking
        
        return reservationRepository.save(reservation);
    }

    @Override
    public int nombreTotalReservations() {
        return reservationRepository.nombreTotalReservations();
    }

    @Override
    public int nombreTotalReservationsAnnulee() {
        return reservationRepository.nombreTotalReservationsAnnulee();
    }

    @Override
    public int nombreTotalReservationsEnCours() {
        return reservationRepository.nombreTotalReservationsEnCours();
    }

}
