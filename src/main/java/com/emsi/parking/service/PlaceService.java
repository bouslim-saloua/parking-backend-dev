package com.emsi.parking.service;

import com.emsi.parking.model.Place;
import java.util.List;

public interface PlaceService {
   // Place ajouter(Place place) throws Exception;
   // Place modifier(Place place) throws Exception;
    List<Place> listePlaces();
    Place getPlaceById(long id) throws Exception;
    List<Place> findAllDisponiblePlaces();
    
    List<Place> findAllReservedPlaces();
    List<Place> findByParkingId(Long parkingId);
    void savePlace(Place place);
    void deletePlace(Long id);
    void updatePlace(Long id, Place updatedPlace);
    Place getPlaceById(Long id);
    List<Place> getAllPlaces();

}
