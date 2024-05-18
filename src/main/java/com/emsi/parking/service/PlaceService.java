package com.emsi.parking.service;

import com.emsi.parking.model.Place;
import java.util.List;

public interface PlaceService {
    Place ajouter(Place place) throws Exception;
    Place modifier(Place place) throws Exception;
    List<Place> listePlaces();
    Place getPlaceById(long id) throws Exception;
}
