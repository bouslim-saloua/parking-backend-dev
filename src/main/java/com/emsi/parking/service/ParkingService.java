package com.emsi.parking.service;

import com.emsi.parking.model.Parking;
import java.util.List;

public interface ParkingService {
    List<Parking> listeParkings();
    Parking getParkingById(long id) throws Exception;
    List<Parking> findAllDisponible();
    List<String> getAllLocations();
    Parking createParking(Parking parking);
    Parking updateParking(Long id, Parking parking) throws Exception;
    void deleteParking(Long id);
   
   
    
    
}
