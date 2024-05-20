package com.emsi.parking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.emsi.parking.model.Place;
import com.emsi.parking.repository.PlaceRepository;
import com.emsi.parking.service.PlaceService;

@Service
@Primary
public class PlaceServiceImpl implements PlaceService{
	
	    private PlaceRepository placeRepository;
	 @Autowired
	    public PlaceServiceImpl(PlaceRepository placeRepository) {
	        this.placeRepository = placeRepository;
	    }

	    @Override
	    public void savePlace(Place place) {
	        placeRepository.save(place);
	    }

	    @Override
	    public void deletePlace(Long id) {
	        placeRepository.deleteById(id);
	    }
	    
	    @Override
	    @Transactional
	    public void updatePlace(Long id, Place updatedPlace) {
	        Optional<Place> optionalPlace = placeRepository.findById(id);
	        if (optionalPlace.isPresent()) {
	            Place place = optionalPlace.get();
	            place.setNumero(updatedPlace.getNumero());
	            place.setReserve(updatedPlace.isReserve());
	            place.setParking(updatedPlace.getParking());
	            placeRepository.save(place);
	        } else {
	            throw new IllegalArgumentException("La place avec l'ID " + id + " n'existe pas");
	        }
	    }
	    
	    @Override
	    public Place getPlaceById(Long id) {
	        return placeRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("La place avec l'ID " + id + " n'existe pas"));
	    }
	    
	    @Override
	    public List<Place> getAllPlaces() {
	        return placeRepository.findAll();
	    }
	    

}
