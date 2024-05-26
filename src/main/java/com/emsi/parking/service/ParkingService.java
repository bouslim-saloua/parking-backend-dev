package com.emsi.parking.service;

import com.emsi.parking.exception.ResourceNotFoundException;
import com.emsi.parking.model.Parking;
import java.util.List;

public interface ParkingService {
    List<Parking> listeParkings();
    Parking getParkingById(long id) throws Exception;
    List<Parking> findAllDisponible();  
    Parking createParking(Parking parking);
    Parking updateParking(Long id, Parking parking) throws Exception;
    void deleteParking(Long id);


    //List<String> getAllAdresses();
    int nombreTotalParking();
    List<Parking> findBySecteur(Long secteurId) throws ResourceNotFoundException;
    List<Parking> getAll();


    
    
}
