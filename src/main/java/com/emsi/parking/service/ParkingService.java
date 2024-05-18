package com.emsi.parking.service;

import com.emsi.parking.model.Parking;
import java.util.List;

public interface ParkingService {
    Parking ajouter(Parking parking) throws Exception;
    Parking modifier(Parking parking) throws Exception;
    List<Parking> listeParkings();
    Parking getParkingById(long id) throws Exception;
}
