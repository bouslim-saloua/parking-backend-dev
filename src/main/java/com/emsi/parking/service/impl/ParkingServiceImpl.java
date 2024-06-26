package com.emsi.parking.service.impl;


import com.emsi.parking.exception.ResourceNotFoundException;

import com.emsi.parking.model.Parking;
import com.emsi.parking.model.Secteur;
import com.emsi.parking.repository.ParkingRepository;
import com.emsi.parking.repository.SecteurRepository;
import org.springframework.stereotype.Service;

import com.emsi.parking.service.ParkingService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Service
public class ParkingServiceImpl implements ParkingService{

    final ParkingRepository parkingRepository;
    final SecteurRepository secteurRepository;


   /* @Override

    final SecteurRepository secteurRepository;
    @Override

    public Parking ajouter(Parking parking) throws Exception{
        Parking parkingFromDB = parkingRepository.findById(parking.getId()).orElse(null);
         if(parkingFromDB != null) throw new Exception("parking with id " + parking.getId() + "already exists");
        return parkingRepository.save(parking);
    }

    @Override
    public Parking modifier(Parking parking) throws Exception{
        Parking parkingFromDB = parkingRepository.findById(parking.getId()).orElse(null);
        if(parkingFromDB == null) throw new Exception("parking with id " + parking.getId() + "doesn't exist");
        return parkingRepository.save(parking);
        
    }*/

    @Override
    public List<Parking> listeParkings() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking getParkingById(long id) throws Exception{
        Parking parkingFromDB = parkingRepository.findById(id).orElse(null);
        if(parkingFromDB == null) throw new Exception("Parking not found");
        return parkingFromDB;
    }

    @Override
    public List<Parking> findAllDisponible() {
        return parkingRepository.findAllDisponible();
    }

    /*@Override
    public List<String> getAllLocations() {
        return parkingRepository.findAll().stream()
                .map(Parking::getLocation)
                .collect(Collectors.toList());
    }
    */
    @Override
    public int nombreTotalParking() {
        return parkingRepository.nombreTotalParking();
    }

    

    @Override
    public Parking createParking(Parking parking) {
        return parkingRepository.save(parking);
    }
    
 /*    @Override
    public Parking updateParking(Long id, Parking updatedParking) {
    Optional<Parking> existingParkingOptional = parkingRepository.findById(id);
    if (existingParkingOptional.isPresent()) {
        Parking existingParking = existingParkingOptional.get();
        existingParking.setNom(updatedParking.getNom());
        existingParking.setAdresse(updatedParking.getAdresse());
        existingParking.setCapacite(updatedParking.getCapacite());
        existingParking.setStatus(updatedParking.getStatus());        
        return parkingRepository.save(existingParking);
    } else {
        throw new RuntimeException("Parking not found with id: " + id);
    }
}*/

   @Override
    public Parking updateParking(Long id, Parking parking) throws Exception {
        Parking parkingFromDB = parkingRepository.findById(id).orElse(null);
        if (parkingFromDB == null) throw new Exception("Parking with id " + id + " doesn't exist");
        parking.setId(id);
        return parkingRepository.save(parking);
    }

    @Override
    public void deleteParking(Long id) {
        parkingRepository.deleteById(id);
    }

    

    @Override
    public List<Parking> findBySecteur(Long secteurId) throws ResourceNotFoundException{
       /* Secteur secteur = secteurRepository.findById(secteurId)
                           .orElseThrow(() -> new ResourceNotFoundException("Secteur not found"));*/
        return parkingRepository.findBySecteurId(secteurId);
    }

    @Override
    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    

   

    
    

}
