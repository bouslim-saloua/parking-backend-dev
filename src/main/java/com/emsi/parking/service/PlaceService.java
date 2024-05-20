package com.emsi.parking.service;

import java.util.List;

import com.emsi.parking.model.Place;

public interface PlaceService {
	void savePlace(Place place);
    void deletePlace(Long id);
    void updatePlace(Long id, Place updatedPlace);
    Place getPlaceById(Long id);
    List<Place> getAllPlaces();
}
