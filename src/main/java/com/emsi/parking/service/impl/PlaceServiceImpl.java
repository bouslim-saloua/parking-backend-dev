package com.emsi.parking.service.impl;

import com.emsi.parking.model.Place;
import com.emsi.parking.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import com.emsi.parking.service.PlaceService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService{
    final PlaceRepository placeRepository;
    

    @Override
    public Place ajouter(Place place) throws Exception {
        Place placeFromDB = placeRepository.findById(place.getId()).orElse(null);
         if(placeFromDB != null) throw new Exception("place with id " + place.getId() + "already exists");
        return placeRepository.save(place);
    }

    @Override
    public Place modifier(Place place) throws Exception {
        Place placeFromDB = placeRepository.findById(place.getId()).orElse(null);
        if(placeFromDB == null) throw new Exception("place with id " + place.getId() + "doesn't exist");
        return placeRepository.save(place);
    }

    @Override
    public List<Place> listePlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Place getPlaceById(long id) throws Exception {
        Place placeFromDB = placeRepository.findById(id).orElse(null);
        if(placeFromDB == null) throw new Exception("place not found");
        return placeFromDB;
    }

    @Override
    public List<Place> findAllDisponiblePlaces() {
        return placeRepository.findAllDisponiblePlaces();
    }

    @Override
    public List<Place> findAllReservedPlaces() {
        return placeRepository.findAllReservedPlaces();
    }

    @Override
    public List<Place> findByParkingId(Long parkingId) {
        return placeRepository.findByParkingId(parkingId);
    }

    

}
