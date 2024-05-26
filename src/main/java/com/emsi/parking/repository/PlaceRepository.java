package com.emsi.parking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Place;
import com.emsi.parking.model.Reservation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value="SELECT p FROM Place p WHERE p.reserve=false")
    List<Place> findAllDisponiblePlaces();
    
    @Query(value="SELECT p FROM Place p WHERE p.reserve = true")
    List<Place> findAllReservedPlaces();
    
    List<Place> findByParkingId(Long parkingId);
    

	Optional<Reservation> findById(Place place);

}
