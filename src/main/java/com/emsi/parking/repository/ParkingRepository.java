package com.emsi.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Secteur;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
///Costumized queries
@Query(value="SELECT p FROM Parking p WHERE p.status='disponible'")
List<Parking> findAllDisponible();

@Query(value="SELECT COUNT(*) FROM Parking")
int nombreTotalParking();

List<Parking> findBySecteur(Secteur secteur);
}
