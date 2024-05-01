package com.emsi.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
